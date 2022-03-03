package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.City;
import com.example.demo.entity.CurrentAccount;
import com.example.demo.entity.History;
import com.example.demo.entity.Parking;

import com.example.demo.security.dto.CurrentAccountDTO;
import com.example.demo.security.dto.TimePriceDTO;
import com.example.demo.security.model.User;
import com.example.demo.security.repositories.UserRepository;
import com.example.demo.service.CityService;
import com.example.demo.service.CurrentAccounService;
import com.example.demo.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	CityService ciudadService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CurrentAccounService ctaCServiceImp;

	@Autowired
	ParkingServiceImp estService;

	@Autowired
	HistoryServiceImp histService;

	public User saveUser(User user) {

		return this.userRepository.save(user);
	}

	public User updateUser(User user) {

		return this.userRepository.save(user);
	}

	public void debitBalance(String username) {
		// Se actualiza la cuenta del usuario, y guarda la operacion en el historial.
		System.out.println("EJECUTANDO DEBITO");
		Optional<User> per = this.existByUsername(username);
		Optional<Parking> est = this.estService.getById(per.get().getParking().getId());
		ArrayList<City> ciudad = this.ciudadService.getAll();
		TimePriceDTO result = per.get().getCurrentAccount().debit(per, est, ciudad.get(0));
		this.updateUser(per.get());
		this.ctaCServiceImp.update(per.get().getCurrentAccount());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fecha = sdf.format(new Date());
		System.out.println("date para historial formateado: " + fecha);
		histService.saveHistory(new History(fecha, "Consumo", per.get().getCurrentAccount().getBalance(),
				result.getPrice(), per.get().getCurrentAccount()));
	}

	public void chargeBalance(CurrentAccountDTO cc) {
		Optional<User> per = this.existByUsername(cc.getPhone());
		Optional<CurrentAccount> cuentaCorriente = this.ctaCServiceImp.getById(cc.getId());
		cuentaCorriente.get().loadBalance(cc.getBalance());
		this.ctaCServiceImp.update(cuentaCorriente.get());
		per.get().getCurrentAccount().setBalance(cuentaCorriente.get().getBalance());
		this.updateUser(per.get());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fecha = sdf.format(new Date());
		System.out.println("date para historial formateado: " + fecha);
		histService.saveHistory(new History(fecha, "Carga", per.get().getCurrentAccount().getBalance(), cc.getBalance(),
				per.get().getCurrentAccount()));
	}

	@Override
	public boolean delete(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Optional<User> getId(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> existByUsername(String nameUser) {
		return userRepository.findByNameUser(nameUser);
	}

	@Override
	public Optional<User> getNameUser(String nameUser) {
		// Obtengo el nombre de usuario y pass de la persona logueada

		Iterable<User> listUser = userRepository.findAll();
		Optional<User> userReg = null;
		for (User user : listUser) {
			if (user.getNameUser().equals(nameUser)) {
				userReg = Optional.ofNullable(user);
			}
		}

		return userReg;
	}

}
