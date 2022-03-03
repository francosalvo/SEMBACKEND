package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Holiday;

@Service

public interface HolidayService {

	ArrayList<Holiday> getAll();

	Holiday save(Holiday feriado);

}
