package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.security.dto.CurrentAccountDTO;
import com.example.demo.security.model.User;

/**
 * 
 * interface que contiene los metodos
 * 
 * @author Fran
 *
 */

@Service
public interface UserService {

	Optional<User> getId(Long id);

	User saveUser(User user);

	User updateUser(User user);

	boolean delete(Long id);

	Optional<User> getNameUser(String nameUser);

	void chargeBalance(CurrentAccountDTO ca);

}
