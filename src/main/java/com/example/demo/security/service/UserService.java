package com.example.demo.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.model.User;
import com.example.demo.security.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> getByNameUser(String nombreUsuario) {
		return userRepository.findByNameUser(nombreUsuario);
	}

	public boolean existsByNameUser(String nombreUsuario) {
		return userRepository.existsByNameUser(nombreUsuario);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
