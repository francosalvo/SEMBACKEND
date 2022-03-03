package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Parking;
import com.example.demo.repository.ParkingRepository;
import com.example.demo.service.ParkingService;

@Service
public class ParkingServiceImp implements ParkingService {

	@Autowired
	ParkingRepository parkingRepository;

	@Override
	public ArrayList<Parking> getAll() {
		return (ArrayList<Parking>) parkingRepository.findAll();
	}

	@Override
	public Parking saveParking(Parking parking) {
		return parkingRepository.save(parking);
	}

	@Override
	public boolean delete(Long id) {
		try {
			parkingRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<Parking> getById(Long id) {
		return parkingRepository.findById(id);
	}

	public Optional<Parking> getParkingStartedByUser(String username) {
		return parkingRepository.findByStartedAndUSer(username);
	}

	public boolean parkingStartedWithPatent(String patent, String username) {
		// return -> true -> hay un estacionamiento iniciado con esa patente.
		return !parkingRepository.getParkingStartedWithPatent(patent, username).isEmpty();
	}

	public boolean parkingStartedWithPatent(String patent, Long id) {
		// return -> true -> hay un estacionamiento iniciado con esa patente.
		return !parkingRepository.getParkingStartedWithPatent(patent, id).isEmpty();
	}

	@Transactional(readOnly = true)
	public Parking findByPatentStarted(String patente) {
		return parkingRepository.findByPatentStarted(patente);
	}

	@Override
	public Parking updateParking(Parking estacionamiento) {
		return parkingRepository.save(estacionamiento);

	}

}
