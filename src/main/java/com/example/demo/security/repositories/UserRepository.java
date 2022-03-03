package com.example.demo.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByNameUser(String nameUser);

	boolean existsByNameUser(String nameUser);

	boolean existsByEmail(String email);

	void deleteById(Long id);

	Optional<User> findById(Long id);

}
