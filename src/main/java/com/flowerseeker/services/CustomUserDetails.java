package com.flowerseeker.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flowerseeker.domain.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Set<GrantedAuthority> authorities;
	private boolean enabled;
	
	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
		this.authorities = new HashSet<GrantedAuthority>();
		if (user.getHasStore()) {
			authorities.add(new SimpleGrantedAuthority("florist"));
		}
		if (user.getIsAdmin())
			authorities.add(new SimpleGrantedAuthority("admin"));
		authorities.add(new SimpleGrantedAuthority("customer"));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
