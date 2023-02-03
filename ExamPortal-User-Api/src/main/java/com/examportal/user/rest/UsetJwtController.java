package com.quiqbook.web.rest;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quiqbook.Constant.AuthoritiesConstants;
import com.quiqbook.Constant.Constants;
import com.quiqbook.Response.APIResponse;
import com.quiqbook.Response.ApiResponseService;
import com.quiqbook.Response.SocialClass;
import com.quiqbook.config.ApplicationProperties;
import com.quiqbook.domain.FailLoginAttempt;
import com.quiqbook.domain.OtpLogin;
import com.quiqbook.domain.ReCaptcha;
import com.quiqbook.models.Authority;
import com.quiqbook.models.Session;
import com.quiqbook.models.User;
import com.quiqbook.models.UserProfile;
import com.quiqbook.repository.FailLoginAttemptRepository;
import com.quiqbook.repository.SessionRepository;
import com.quiqbook.repository.UserProfileRepository;
import com.quiqbook.repository.UserRepository;
import com.quiqbook.security.jwt.JWTFilter;
import com.quiqbook.security.jwt.TokenProvider;
import com.quiqbook.service.MailService;
import com.quiqbook.service.SessionService;
import com.quiqbook.service.UserService;
import com.quiqbook.service.util.RandomUtil;
import com.quiqbook.web.rest.vm.LoginVM;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/qbkuser")
@Description("Operations pertaining to authentication,OTP based authetication,logout from system")
public class UserJWTController {

	private final TokenProvider tokenProvider;

	private final AuthenticationManager authenticationManager;

	@Autowired
	private MailService mailService;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SessionService sessionService;
	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	ApiResponseService apiResponseService;

	@Autowired
	FailLoginAttemptRepository failLoginAttemptRepository;

	@Autowired
	ApplicationProperties applicationProperties;

	public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	// don't remove
	@GetMapping("/health/check")
	public Object healthCheck() {
		return apiResponseService.success("Health check", null);
	}

	@PostMapping("/authenticate")
	public Object authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

		Optional<User> userCheckForActivate;
		if (loginVM.getLogin().contains("@")) {
			userCheckForActivate = userRepository.findOneByEmailIgnoreCase(loginVM.getLogin());
			if (userCheckForActivate.isPresent()) {
				loginVM.setLogin(userCheckForActivate.get().getLogin());
			}

		} else {
			userCheckForActivate = userRepository.findOneByLogin(loginVM.getLogin());
			if (!userCheckForActivate.isPresent()) {
				userCheckForActivate = userRepository.findOneByMobileNumber(Long.parseLong(loginVM.getLogin()));
				loginVM.setLogin(userCheckForActivate.get().getLogin());
			}

		}
		
		if (!userCheckForActivate.isPresent()) {
			return apiResponseService.failure(401, "You are not registered with us. Please Register!");
		} else if (userCheckForActivate.isPresent() && !userCheckForActivate.get().getActivated()) {
			return apiResponseService.failureSocial(401, "User not activated", "account_not_activated", 5);
		}
		

		else if (userCheckForActivate.isPresent() && "yes".equalsIgnoreCase(userCheckForActivate.get().getUserLock())) {
			Instant nowInstant = Instant.now();
			Duration res = Duration.between(userCheckForActivate.get().getLockDate(), nowInstant);

			if (res.getSeconds() <= Integer.parseInt(applicationProperties.getAccountBlockSec())) {
				return apiResponseService.failureSocial(401, "Your account was blocked please wait for one minute",
						"account_locked", 5);
			} else {
				User user = userCheckForActivate.get();
				user.setUserLock("no");
				user.setLockDate(null);
				userRepository.save(user);

				List<FailLoginAttempt> failLoginAttemptList = failLoginAttemptRepository
						.findAllByLogin(userCheckForActivate.get().getLogin());
				if (!failLoginAttemptList.isEmpty()) {
					failLoginAttemptRepository.deleteAll(failLoginAttemptList);
				}
			}

		}

		User user = userCheckForActivate.get();
		
		Optional<UserProfile> userProfile = userProfileRepository.findByProfileId(user.getUserId());
		
		if(userProfile.isPresent()&&userProfile.get().getStatus().equalsIgnoreCase("INACTIVE"))
		{
			return apiResponseService.failureSocial(401, "User not activated", "account_not_activated", 0);
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginVM.getLogin(), loginVM.getPassword());

		Calendar date = Calendar.getInstance();
		long timeInMiliSec = date.getTimeInMillis();
		Date afterMinusMins = new Date(timeInMiliSec - (Constants.MINUTE));
		List<FailLoginAttempt> failLoginAttemptList = failLoginAttemptRepository
				.findAllBywrongPasswordAttemptDate(userCheckForActivate.get().getLogin(), afterMinusMins);
		String jwt = "";
		try {
			if (failLoginAttemptList.size() >= 3 && failLoginAttemptList.size() <= 4) {
				String url = null;
				if ((!"".equalsIgnoreCase(loginVM.getLoggedFrom())) && (loginVM.getLoggedFrom() != null)) {
					if (loginVM.getLoggedFrom().equalsIgnoreCase("ANDROID")) {
						url = applicationProperties.getGoogleCaptchaUrlForAndroid() + loginVM.getRecaptcha();
					}
				} else {
					url = applicationProperties.getGoogleCaptchaUrlForWeb() + loginVM.getRecaptcha();
				}
				HttpHeaders headers = new HttpHeaders();
				HttpEntity<ReCaptcha> requestEntity = new HttpEntity<>(null, headers);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> responseFromReCaptcha = restTemplate.exchange(url, HttpMethod.POST,
						requestEntity, String.class);
				if (responseFromReCaptcha.getStatusCode().is2xxSuccessful()) {

					JSONObject jsonObject = new JSONObject(responseFromReCaptcha.getBody());
					if (jsonObject.getBoolean("success")) {

						FailLoginAttempt failLoginAttempt = new FailLoginAttempt();
						failLoginAttempt.setLogin(loginVM.getLogin());
						failLoginAttempt.setWrongPasswordAttemptDate(Instant.now());
						failLoginAttemptRepository.save(failLoginAttempt);

						if (failLoginAttemptList.size() >= Integer
								.parseInt(applicationProperties.getPasswordAttempt())) {
							user.setUserLock("yes");
							user.setLockDate(Instant.now());
							userRepository.save(user);
						}

						if (failLoginAttemptList.size() == 4) {
							return apiResponseService.failureSocial(401,
									"Your account was blocked please wait for one minute", "account_locked", 4);
						}
						return apiResponseService.failureSocial(401, "Invalid credential", "invalid_credential",
								failLoginAttemptList.size());

					}
				}
			}

			Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
			jwt = tokenProvider.createToken(authentication, rememberMe);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

			if (!failLoginAttemptList.isEmpty()) {
				failLoginAttemptRepository.deleteAll(failLoginAttemptList);
			}

			user.setUserLock("no");
			user.setLockDate(null);
			userRepository.save(user);

			User userData = new User();
			if (loginVM.getLogin().contains("@")) {
				Optional<User> userCheck = userRepository.findOneWithAuthoritiesByEmail(loginVM.getLogin());
				if (userCheck.isPresent()) {
					userData = userCheck.get();
				}

			} else {
				Optional<User> userCheck = userRepository.findOneWithAuthoritiesByLogin(loginVM.getLogin());
				if (userCheck.isPresent()) {
					userData = userCheck.get();
				}
			}
			List<Session> sessionList = sessionService.findByUserId(userProfile.get().getProfileId());
			if (sessionList != null && !sessionList.isEmpty()) {
				sessionRepository.deleteAll(sessionList);
			}

			Session session = new Session(userProfile.get().getProfileId(), jwt, loginVM.getLoggedFrom());
			sessionService.save(session);

			APIResponse APIResponse = new APIResponse();
			SocialClass socialClass = new SocialClass();

			for (Authority authority : userData.getAuthorities()) {
				if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
					socialClass.setIdentity("Admin");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.PROVIDER)) {
					socialClass.setIdentity("Provider");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.MEMBER)) {
					socialClass.setIdentity("Member");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.PHARMACIST)) {
					socialClass.setIdentity("Pharmacist");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.RECEPTIONIST)) {
					socialClass.setIdentity("Receptionist");
				}

			}
			socialClass.setProfileId(userProfile.get().getProfileId());
			socialClass.setIdToken(jwt);
			APIResponse.setMessage("Login successfully");
			APIResponse.setStatus("Success");
			APIResponse.setStatusCode(200);
			APIResponse.setData(socialClass);
			return APIResponse;

		} catch (Exception e) {
			if ("yes".equalsIgnoreCase(userCheckForActivate.get().getUserLock())) {
				return apiResponseService.failureSocial(401, "Your account was blocked please wait for one minute",
						"account_locked", 5);
			}

			FailLoginAttempt failLoginAttempt = new FailLoginAttempt();
			failLoginAttempt.setLogin(loginVM.getLogin());
			failLoginAttempt.setWrongPasswordAttemptDate(Instant.now());
			failLoginAttemptRepository.save(failLoginAttempt);

			if (failLoginAttemptList.size() >= Integer.parseInt(applicationProperties.getPasswordAttempt())) {
				user.setUserLock("yes");
				user.setLockDate(Instant.now());
				userRepository.save(user);
			}

			return apiResponseService.failureSocial(401, "Invalid credential", "invalid_credential",
					failLoginAttemptList.size());
		}

	}

	@GetMapping("/otp")
	public Object loginWithOtp1() {
		User user = new User();
		user.setMobileNumber(7719898526L);
		return userService.sendLoginOtpMessage1(user);

	}

	@ApiOperation(value = "Login using OTP")
	@PostMapping("/login/otp")
	public Object loginWithOtp(@Valid @RequestBody OtpLogin loginVM) {
		User user = new User();
		Optional<User> userCheck;
		if (loginVM.getLogin().contains("@")) {
			userCheck = userRepository.findOneWithAuthoritiesByEmail(loginVM.getLogin());
		} else {
			userCheck = userRepository.findOneByMobileNumber(Long.parseLong(loginVM.getLogin()));

		}
		if (userCheck.isPresent()) {
			user = userCheck.get();
			String otp = RandomUtil.generateOtp();
			user.setResetKey(otp);
			if (user.getEmail() != null && !"".equalsIgnoreCase(user.getEmail())) {
				mailService.sendLoginOtp(user);
			} else if (user.getMobileNumber() != null) {
				userService.sendLoginOtpMessage(user);
			} else {
				return apiResponseService.failureSocial(404, "Mobile number or email is required to send OTP",
						"invalid_data", 0);
			}
			user.setActivationKey(otp);
			userRepository.save(user);
			return apiResponseService.success("OTP sent Successfully", null);

		} else {
			return apiResponseService.failureSocial(401, "Invalid user", "invalid_user", 0);
		}

	}

	@ApiOperation(value = "Verification of provided OTP for login")
	@PostMapping("/login/otp/verified")
	public Object authorize(@Valid @RequestBody OtpLogin loginVM) {

		Optional<User> userCheck;
		if (loginVM.getLogin().contains("@")) {
			userCheck = userRepository.findOneWithAuthoritiesByEmail(loginVM.getLogin());
		} else {
			userCheck = userRepository.findOneByMobileNumber(Long.parseLong(loginVM.getLogin()));

		}

		if (userCheck.isPresent() && loginVM.getOtp().equals(userCheck.get().getResetKey())) {

			String jwt = tokenProvider.createTokenUsingOtp(userCheck.get(), false);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
			Optional<UserProfile> userProfile = userProfileRepository.findByProfileId(userCheck.get().getUserId());

			APIResponse APIResponse = new APIResponse();
			SocialClass socialClass = new SocialClass();

			for (Authority authority : userCheck.get().getAuthorities()) {
				if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
					socialClass.setIdentity("Admin");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.PROVIDER)) {
					socialClass.setIdentity("Provider");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.MEMBER)) {
					socialClass.setIdentity("Member");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.PHARMACIST)) {
					socialClass.setIdentity("Pharmacist");
				} else if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.RECEPTIONIST)) {
					socialClass.setIdentity("Receptionist");
				}

			}

			List<Session> sessionList = sessionService.findByUserId(userProfile.get().getProfileId());
			if (sessionList != null && !sessionList.isEmpty()) {
				sessionRepository.deleteAll(sessionList);
			}

			Session session = new Session(userProfile.get().getProfileId(), jwt, loginVM.getLoggedFrom());
			sessionService.save(session);
			socialClass.setProfileId(userProfile.get().getProfileId());
			socialClass.setIdToken(jwt);
			APIResponse.setMessage("Login successfully");
			APIResponse.setStatus("Success");
			APIResponse.setStatusCode(200);
			APIResponse.setData(socialClass);
			return APIResponse;

		} else {
			return apiResponseService.failureSocial(401, "Invalid user", "invalid_user", 0);
		}

	}

	@GetMapping("/tokenValidate/{token}")
	public Object validateToken(@PathVariable String token) {

		if (token.contains("Bearer")) {
			token = token.replace("Bearer", "");
			token = token.trim();
		}

		boolean isTokenValidate = tokenProvider.validateToken(token);
		if (isTokenValidate) {
			return apiResponseService.success("token is valid", null);
		} else {
			return apiResponseService.failure(401, "token is expired or invalid");
		}

	}

	@DeleteMapping("/logout/{userId}/{loggedFrom}") // userId=pass profile id from userProfile (maintain this to check
													// for chat user is active or not)
	public Object deleteSession(@PathVariable Long userId, @PathVariable String loggedFrom) {
		Session session = sessionRepository.findByUserIdAndLoggedFrom(userId, loggedFrom);
		if (session != null) {
			sessionService.delete(session.getSessionId());
			return apiResponseService.success("Session deleted successfully", null);
		} else {
			return apiResponseService.success("Session deleted successfully", null);
		}
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class JWTToken {

		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}
}
