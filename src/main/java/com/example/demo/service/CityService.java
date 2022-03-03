package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.City;

@Service
public interface CityService {

	ArrayList<City> getAll();

	City saveCity(City ciudad);

	City upDate(City ciudad);

	boolean delete(Long id);

	Optional<City> getById(Long id);

}
