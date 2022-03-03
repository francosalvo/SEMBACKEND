package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.security.dto.CurrentAccountDTO;
import com.example.demo.security.dto.DataAccountUserDTO;
import com.example.demo.security.dto.Message;
import com.example.demo.security.model.User;
import com.example.demo.service.*;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.ParkingServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	CurrentAccounService ccServiceImp;

	@Autowired
	ParkingServiceImp parkingService;

	@Autowired
	CityService cityService;

	@Autowired
	UserService usersServiceImpl;

	@Autowired
	PatenteService patentesServiceImpl;

	@GetMapping(path = "patents/{username}")
	public ResponseEntity<Set<Patent>> getPatentsById(@PathVariable("username") String username) {
		// get all the patents of a user by id
		Optional<User> user = this.usersServiceImpl.getNameUser(username);
		Set<Patent> listPatents = this.patentesServiceImpl.getByIdUser(user.get().getId());
		return new ResponseEntity<Set<Patent>>(listPatents, HttpStatus.OK);
	}

	@GetMapping(path = "/getData/{username}")
	public ResponseEntity<DataAccountUserDTO> getByUsername(@PathVariable("username") String username) {
		// Me traigo la data por el nombre de usuario
		Optional<User> user = this.usersServiceImpl.getNameUser(username);
		if (user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			DataAccountUserDTO accountData = new DataAccountUserDTO(user.get().getName(),
					user.get().getNameUser(), user.get().getEmail(), user.get().getCurrentAccount());
			return new ResponseEntity<DataAccountUserDTO>(accountData, HttpStatus.OK);
		}

	}

	@GetMapping(path = "/getCurrentAccount/{username}")
	public ResponseEntity<CurrentAccount> getCurrentAccount(@PathVariable("username") String username) {
		// obtengo la cuenta actual por el nombre de usuario
		Optional<User> user = this.usersServiceImpl.getNameUser(username);
		return new ResponseEntity<CurrentAccount>(user.get().getCurrentAccount(), HttpStatus.OK);
	}

	@PostMapping()
	public User saveUser(@RequestBody User user) {
		// REQUEST BODY, O SEA, TODOS LOS CLIENTES PUEDEN ENVIAR INFO DENTRO DE LA
		// SOLICITUD HTTP
		return this.usersServiceImpl.updateUser(user);
	}

	// carga de saldo a la cuenta UserName
	@PostMapping("/chargeBalance")
	public ResponseEntity<?> chargeBalance(@Valid @RequestBody CurrentAccountDTO ca, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		this.usersServiceImpl.chargeBalance(ca);
		return new ResponseEntity<Message>(new Message("Se ha actualizado el saldo"), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		System.out.println("Actualizando el usuario " + user.getId());
		Optional<User> currentUser = usersServiceImpl.getId(user.getId());

		if (currentUser.isEmpty()) {
			System.out.println("User with id " + user.getId() + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			this.usersServiceImpl.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<User> deleteById(@PathVariable("id") Long id) {
		// delete a user by id
		boolean ok = this.usersServiceImpl.delete(id);
		if (!ok) {
			System.out.println("No es posible eliminar, no se encuentra el usuario con id " + id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Se elimino el usuario con id " + id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}

}
