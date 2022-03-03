package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    CityRepository ciudadRepository;

    public ArrayList<City> getAll() {
        return (ArrayList<City>) ciudadRepository.findAll();
    }

    public City saveCity(City c) {
        return ciudadRepository.save(c);
    }

    public Optional<City> getById(Long id) {
        return ciudadRepository.findById(id);
    }

    public boolean delete(Long id) {
        try {
            ciudadRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public City upDate(City ciudad) {
        return ciudadRepository.save(ciudad);
    }

}
