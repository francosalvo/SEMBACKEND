package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

	@Query(value = "SELECT * FROM Parking e WHERE e.started = 1 and e.username = :username", nativeQuery = true)
	Optional<Parking> findByStartedAndUSer(@Param("username") String username);

	@Query(value = "SELECT * FROM Parking e WHERE e.started = 1 and e.patent=?1 and e.username =?2", nativeQuery = true)
	Optional<Parking> getParkingStartedWithPatent(String patent, String username);

	@Query(value = "SELECT * FROM Parking e WHERE e.started = 1 and e.patent=?1 and e.username=?2", nativeQuery = true)
	Optional<Parking> getParkingStartedWithPatent(String patent, Long id);

	@Query(value = "SELECT * FROM Parking e WHERE e.started = 1 and e.patent=?1", nativeQuery = true)
	Parking findByPatentStarted(String patent);

}
