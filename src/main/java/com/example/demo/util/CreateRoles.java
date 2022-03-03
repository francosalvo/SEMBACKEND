package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.City;
import com.example.demo.entity.Holiday;
import com.example.demo.security.enums.RolName;
import com.example.demo.security.model.Rol;
import com.example.demo.security.service.RolService;
import com.example.demo.service.CityService;
import com.example.demo.service.HolidayService;


@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;
    
    @Autowired
	CityService ciudadService;
	@Autowired
	 HolidayService HolidayServiceImp;

    @Override
    public void run(String... args) throws Exception {
    	String holidayList = "01/01,21/02,28/02,01/03,24/03,02/04,15/04,01/05,25/05,20/06,09/07,08/12,25/12,17/06,15/08,10/10,20/11,07/10,21/11,09/12";
    	
    	
    	
    	if (rolService.getAll().isEmpty()) {
			Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
			Rol rolUser = new Rol(RolName.ROLE_USER);
			rolService.save(rolAdmin);
			rolService.save(rolUser);
		}
    	
    	List<City> listaCiudades = ciudadService.getAll();
		if (listaCiudades.isEmpty()) {
			City nuevaCiudad = new City("8-20", 10);
			ciudadService.saveCity(nuevaCiudad);
		}

		if (HolidayServiceImp.getAll().isEmpty()) {
			String[] list = holidayList.split((","));
			for (String elem : list) {
				HolidayServiceImp.save(new Holiday(elem));
			}
		}

    }
}
