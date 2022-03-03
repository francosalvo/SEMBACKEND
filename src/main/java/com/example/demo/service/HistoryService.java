package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.History;

@Service
public interface HistoryService {

	ArrayList<History> getAll();

	History saveHistory(History historial);

	List<History> getByCurrentAccountId(Long id);

}
