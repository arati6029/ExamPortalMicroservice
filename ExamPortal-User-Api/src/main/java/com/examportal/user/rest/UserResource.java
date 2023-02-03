package com.quiqbook.web.rest;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.quiqbook.Constant.AuthoritiesConstants;
import com.quiqbook.Constant.Constants;
import com.quiqbook.Response.APIResponse;
import com.quiqbook.Response.ApiResponseService;
import com.quiqbook.config.ApplicationProperties;
import com.quiqbook.models.Authority;
import com.quiqbook.models.Notification;
import com.quiqbook.models.User;
import com.quiqbook.repository.UserRepository;
import com.quiqbook.service.MailService;
import com.quiqbook.service.UserService;
import com.quiqbook.service.dto.UserDTO;
import com.quiqbook.web.rest.errors.EmailAlreadyUsedException;
import com.quiqbook.web.rest.errors.LoginAlreadyUsedException;
import com.quiqbook.web.rest.vm.ManagedUserVM;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * REST controller for managing user.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of
 * authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no View Model and DTO, a lot less code, and an outer-join which
 * would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our user' application
 * because of this use-case.</li>
 * <li>Not having an outer join causes n+1 requests to the database. This is not
 * a real issue as we have by default a second-level cache. This means on the
 * first HTTP call we do the n+1 requests, but then all authorities come from
 * the cache, so in fact it's much better than doing an outer join (which will
 * get lots of data from the database, for each HTTP call).</li>
 * <li>As this manages user, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.
 */
@RestController
@RequestMapping("/qbkuser")
@ApiIgnore
@Description("Operations pertaining to register doctor/patient/admin")
public class UserResource {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	private final UserService userService;

	private final UserRepository userRepository;

	private final MailService mailService;

	@Autowired
	ApiResponseService apiResponseService;

	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	APIResponse apiResponse;

	public UserResource(UserService userService, UserRepository userRepository, MailService mailService) {

		this.userService = userService;
		this.userRepository = userRepository;
		this.mailService = mailService;
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	/**
	 * POST /user : Creates a new user.
	 * <p>
	 * Creates a new user if the login and email are not already used, and sends an
	 * mail with an activation link. The user needs to be activated on creation.
	 *
	 * @param userDTO
	 *            the user to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         user, or with status 400 (Bad Request) if the login or email is
	 *         already in use
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws BadRequestAlertException
	 *             400 (Bad Request) if the login or email is already in use
	 */

	@ApiIgnore
	@PostMapping("/user")
	// @ResponseStatus(HttpStatus.CREATED)
	public Object createUser(@Valid @RequestBody ManagedUserVM managedUserVM) {
		if (!checkPasswordLength(managedUserVM.getPassword())) {
			return apiResponseService.failure(400, "Pasword must be greater than one char");
		}
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex); // Compile the ReGex
		Matcher m = p.matcher(managedUserVM.getPassword()); // Pattern class contains matcher() method to find matching
															// // between given password and regular expression.
		if (!m.matches()) {
			return apiResponseService.failure(400, "Invalid Password..Password doesn't meet Regex criteria");
		}
		if (!managedUserVM.getLogin().contains("@")
				&& userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).isPresent()) {
			return apiResponseService.failure(400, "Login name already present");
		} else if (managedUserVM.getLogin().contains("@")
				&& userRepository.findOneByEmailIgnoreCase(managedUserVM.getLogin()).isPresent()) {
			return apiResponseService.failure(400, "Email already present");
		}
		String emailOfAdmin = null;
		RestTemplate restTemplate = new RestTemplate();
		User user = userService.registerUser(managedUserVM);
		if (user == null) {
			return apiResponseService.failure(400, "User not created successfully");
		}
		List<User> userList = userRepository.findAll();
		for (User userObj : userList) {
			for (Authority authority : userObj.getAuthorities()) {
				if (authority.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
					emailOfAdmin = userObj.getEmail();
				}
			}
		}
		// Registration with Email ID
		if (user.getEmail() != null && !"".equalsIgnoreCase(user.getEmail())) {
			// Provider Registration
			if (!user.getActivated()) {
				// Send registration success email to provider on
				// registered email
				mailService.sendCreationEmail(user);

				// Send registration success email to admin for
				// Account and Profile Verification and activation
				mailService.sendActivationEmailToAdmin(emailOfAdmin);
/*
				// Send Notification to system Admin
				String urlToCreateNotification = applicationProperties.getAppointmentUrl() + "/notification";
				Notification notication = new Notification();
				notication.setType("Registration");
				notication.setProviderName(user.getFirstName());
				Notification saveNotification = restTemplate.postForObject(urlToCreateNotification, notication,
						Notification.class);
				if (saveNotification == null) {
					return apiResponseService.failure(400, "Notification not created");

				}
                */
			} else {
				// Member Registration
				mailService.sendRegistrationEmail(user);
			}
		} else if (user.getMobileNumber() != null) {
			// Provider Registration
			if (!user.getActivated()) {
				// Send registration message email to registered
				// user
				userService.registrationSMS(user);

				// Send registration success message to admin for
				// Account and Profile Verification and activation
				mailService.sendActivationEmailToAdmin(emailOfAdmin);
	/*			// Send Notification to system Admin
				String urlToCreateNotification = applicationProperties.getAppointmentUrl() + "/notification";
				Notification notication = new Notification();
				notication.setType("Registration");
				notication.setProviderName(user.getFirstName());
				Notification saveNotification = restTemplate.postForObject(urlToCreateNotification, notication,
						Notification.class);
				if (saveNotification == null) {
					return apiResponseService.failure(400, "Notification not created");

				}
                */
			} else {
				// Member Registration
				userService.registrationSMSToMember(user);
			}

		}
		return apiResponseService.success("User created successfully", user);

	}

	/**
	 * PUT /user : Updates an existing User.
	 *
	 * @param userDTO
	 *            the user to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         user
	 * @throws EmailAlreadyUsedException
	 *             400 (Bad Request) if the email is already in use
	 * @throws LoginAlreadyUsedException
	 *             400 (Bad Request) if the login is already in use
	 */
	@PutMapping("/user")
	@PreAuthorize("@authentication.hasPermisionToAdmin('','','USER')")
	public Object updateUser(@Valid @RequestBody UserDTO userDTO) {
		log.debug("REST request to update User : {}", userDTO);
		Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
		if (existingUser.isPresent() && (!existingUser.get().getUserId().equals(userDTO.getUserId()))) {
			return apiResponseService.failure(400, "Login name already present");
		}
		existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
		if (existingUser.isPresent() && (!existingUser.get().getUserId().equals(userDTO.getUserId()))) {
			return apiResponseService.failure(400, "Email already present");
		}
		Optional<UserDTO> updatedUser = userService.updateUser(userDTO);

		apiResponse = new APIResponse();
		apiResponse.setMessage("User created successfully");
		apiResponse.setStatus("Success");
		apiResponse.setStatusCode(200);
		apiResponse.setData(updatedUser);
		return apiResponse;
	}

	/**
	 * GET /user : get all user.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all user
	 */

	@GetMapping("/user")
	public Object getAllUsers() {
		List<User> userList = userRepository.findAll();

		if (userList.isEmpty()) {
			return apiResponseService.failure(400, "User not found");
		}
		return apiResponseService.success("User found successfully", userList);
	}

	/**
	 * @return a string list of the all of the roles
	 */
	@GetMapping("/user/authorities")
	@PreAuthorize("@authentication.hasPermisionToAdmin('','','USER')")
	public List<String> getAuthorities() {
		return userService.getAuthorities();
	}

	/**
	 * GET /user/:login : get the "login" user.
	 *
	 * @param login
	 *            the login of the user to find
	 * @return the ResponseEntity with status 200 (OK) and with body the "login"
	 *         user, or with status 404 (Not Found)
	 */

	@GetMapping("/user/{userId}")
	public Object getUser(@PathVariable Long userId) {

		Optional<User> user = userRepository.findOneWithAuthoritiesByUserId(userId);

		if (!user.isPresent()) {
			return apiResponseService.failure(400, "User not found");
		}
		return apiResponseService.success("User found successfully", user);

	}

	@ApiIgnore(value = "used for adding record")
	@GetMapping("/userGet/{login}")
	public Object getUser(@PathVariable String login) {
		Optional<User> user = userRepository.findOneByLogin(login);

		return apiResponseService.success("User found successfully", user);

	}

	/**
	 * DELETE /user/:login : delete the "login" User.
	 *
	 * @param login
	 *            the login of the user to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@DeleteMapping("/user/{login:" + Constants.LOGIN_REGEX + "}")
	@PreAuthorize("@authentication.hasPermisionToAdmin('','','USER')")
	public Object deleteUser(@PathVariable String login) {
		log.debug("REST request to delete User: {}", login);

		try {
			userService.deleteUser(login);
			return apiResponseService.success("User deleted successfully", null);
		} catch (Exception e) {
			return apiResponseService.failure(400, "User not deleted successfully");
		}

	}

	private static boolean checkPasswordLength(String password) {
		return !StringUtils.isEmpty(password) && password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH
				&& password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
	}
}
