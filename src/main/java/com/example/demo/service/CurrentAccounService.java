package com.example.demo.service;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CurrentAccount;

@Service
public interface CurrentAccounService {

	ArrayList<CurrentAccount> getAll();

	CurrentAccount save(CurrentAccount cc);

	CurrentAccount update(CurrentAccount cc);

	boolean delete(Long id);

	Optional<CurrentAccount> getById(Long id);
}
