package com.example.demo.security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMain implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String nameUser;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserMain(String nombre, String nameUser, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = nombre;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserMain build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());
		return new UserMain(user.getName(), user.getNameUser(), user.getEmail(),
				user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return nameUser;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
