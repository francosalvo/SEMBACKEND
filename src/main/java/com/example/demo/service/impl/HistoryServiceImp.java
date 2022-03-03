package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.History;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.HistoryService;

@Service
public class HistoryServiceImp implements HistoryService {

	@Autowired
	HistoryRepository historyRepository;

	@Override
	public ArrayList<History> getAll() {
		return (ArrayList<History>) historyRepository.findAll();
	}

	@Override
	public History saveHistory(History historial) {
		return historyRepository.save(historial);
	}

	@Override
	public List<History> getByCurrentAccountId(Long id) {
		return historyRepository.getByCurrentAccountId(id);
	}
}
