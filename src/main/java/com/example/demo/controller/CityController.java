package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.City;
import com.example.demo.service.impl.CityServiceImp;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityServiceImp ciudadServiceImp;

    @GetMapping
    public ArrayList<City> getAllCitys() {
        // me traigo la lsita de las ciudades
        return ciudadServiceImp.getAll();
    }

    @PostMapping
    public ResponseEntity<City> saveCity(@RequestBody City city) {
        // guardo una ciudad
        return new ResponseEntity<City>(this.ciudadServiceImp.saveCity(city), HttpStatus.CREATED);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<City>> getCityById(@PathVariable("id") Long id) {
        // get city by id
        return new ResponseEntity<Optional<City>>(this.ciudadServiceImp.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<City> updateUser(@RequestBody City city) {
        System.out.println("Actualizando la ciudad " + city.getId());
        Optional<City> currentCity = ciudadServiceImp.getById(city.getId());

        if (currentCity.isEmpty()) {
            System.out.println("Ciudad with id " + city.getId() + " not found");
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        } else {
            this.ciudadServiceImp.upDate(city);
            return new ResponseEntity<City>(city, HttpStatus.OK);
        }

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<City> deleteById(@PathVariable("id") Long id) {
        boolean ok = this.ciudadServiceImp.delete(id);
        if (ok) {
            System.out.println("No es posible eliminar, no se encuentra la ciudad con id " + id);
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("Se elimino la ciudad con id " + id);
            return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
        }
    }
}
