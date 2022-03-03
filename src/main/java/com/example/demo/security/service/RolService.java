package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.security.enums.RolName;
import com.example.demo.security.model.Rol;
import com.example.demo.security.repositories.RolRepository;

@Service
@Transactional
public class RolService {
	@Autowired
	RolRepository rolRepository;

	public Optional<Rol> getByRolName(RolName rolName) {
		return rolRepository.findByRolName(rolName);
	}

	public void save(Rol rol) {
		rolRepository.save(rol);
	}

	public ArrayList<Rol> getAll() {
		return (ArrayList<Rol>) rolRepository.findAll();
	}

	
}
