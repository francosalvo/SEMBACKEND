package com.example.demo.service.impl;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CurrentAccount;
import com.example.demo.repository.CurrentAccountRepository;
import com.example.demo.service.CurrentAccounService;

@Service
public class CurrentAccountServiceImpl implements CurrentAccounService {

	@Autowired
	private CurrentAccountRepository currentAccountRepository;

	@Override
	public ArrayList<CurrentAccount> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrentAccount save(CurrentAccount cc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrentAccount update(CurrentAccount cc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<CurrentAccount> getById(Long id) {

		return this.currentAccountRepository.findById(id);
	}

}
