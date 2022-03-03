package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patent;

import com.example.demo.repository.PatentRepository;
import com.example.demo.service.PatenteService;

@Service
public class PatenteServiceImpl implements PatenteService {

	@Autowired
	private PatentRepository patentRepository;

	@Override
	public Patent guardarPatentes(Patent patent) {

		return this.patentRepository.save(patent);
	}

	@Override
	@Transactional
	public Patent uptdatePatent(Patent patentRequest, Long id) {
		Optional<Patent> patent = this.patentRepository.findById(id);
		if (patent.isPresent()) {
			patent.get().setPatent(patentRequest.getPatent());
			patent.get().setUser(patentRequest.getUser());
			return this.patentRepository.save(patent.get());
		} else
			return null;
	}

	public boolean delete(Long id) {
		try {
			patentRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Set<Patent> getByIdUser(Long idUser) {
		return patentRepository.getByIdUser(idUser);
	}

	@Override
	public Patent existsByPatentAndUser(String patent, Long idUser) {
		// TODO Auto-generated method stub
		return patentRepository.existsByPatenteAndUsuario(patent, idUser);
	}

	@Override
	public Patent getPatentByIdUser(Patent patent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Patent> getById(Long id) {

		return patentRepository.findById(id);
	}

}
