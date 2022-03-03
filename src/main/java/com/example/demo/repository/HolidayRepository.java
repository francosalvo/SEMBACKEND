package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday, Long> {

	Optional<Holiday> getByDate(String date);

}
