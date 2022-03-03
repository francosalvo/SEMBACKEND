package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Holiday;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.service.HolidayService;

@Service
public class HolidayServiceImp implements HolidayService {

	@Autowired
	HolidayRepository holidayRepository;

	public ArrayList<Holiday> getAll() {
		return (ArrayList<Holiday>) holidayRepository.findAll();
	}

	public Holiday save(Holiday feriado) {
		return holidayRepository.save(feriado);
	}

	public Optional<Holiday> getByDate(String fecha) {
		return holidayRepository.getByDate(fecha);
	}

}
