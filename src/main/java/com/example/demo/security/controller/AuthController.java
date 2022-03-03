package com.example.demo.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CurrentAccount;
import com.example.demo.security.dto.JwtDto;
import com.example.demo.security.dto.LoginUsuario;

import com.example.demo.security.dto.Message;
import com.example.demo.security.dto.NewUser;
import com.example.demo.security.enums.RolName;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.model.Rol;
import com.example.demo.security.model.User;
import com.example.demo.security.service.RolService;
import com.example.demo.security.service.UserService;
import com.example.demo.service.CurrentAccounService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	CurrentAccounService currentAccountService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (userService.existsByNameUser(newUser.getNameUser()))
			return new ResponseEntity(new Message("ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
		if (userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
		User user = new User(newUser.getName(), newUser.getNameUser(),
				newUser.getEmail(),
				passwordEncoder.encode(newUser.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
		if (newUser.getRol().contains("admin"))
			roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
		user.setRoles(roles);

		CurrentAccount ca = new CurrentAccount();
		ca.setBalance(0);
		ca.setUser(user);
		ca.setPhone(user.getNameUser());
		user.setCurrentAccount(ca);
		userService.save(user);
		currentAccountService.save(ca);
		return new ResponseEntity(new Message("usuario guardado exitosamente"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNameUser(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
}
