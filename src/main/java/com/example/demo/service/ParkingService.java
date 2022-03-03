package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Parking;

@Service
public interface ParkingService {

	ArrayList<Parking> getAll();

	Parking saveParking(Parking estacionamiento);

	Parking updateParking(Parking estacionamiento);

	boolean delete(Long id);

	Optional<Parking> getById(Long id);

}
