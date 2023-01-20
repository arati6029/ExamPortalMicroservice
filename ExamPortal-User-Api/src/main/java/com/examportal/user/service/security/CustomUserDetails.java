package com.examportal.user.service.security;



import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.examportal.user.model.BaseUser;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	private BaseUser authUserDetails;

	public CustomUserDetails(BaseUser authUserDetails) {
		super();
		this.authUserDetails = authUserDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Map   role   -----> Collection : GrantedAuthority <----SimpleGrantedAuthority(String authority)		
		return Arrays.asList(new SimpleGrantedAuthority(authUserDetails.getRole().name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return authUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return authUserDetails.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
