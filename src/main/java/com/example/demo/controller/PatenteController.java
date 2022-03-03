package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.NewPatent;
import com.example.demo.entity.Parking;
import com.example.demo.entity.Patent;
import com.example.demo.security.dto.Message;
import com.example.demo.security.model.User;
import com.example.demo.service.CityService;
import com.example.demo.service.PatenteService;
import com.example.demo.service.impl.ParkingServiceImp;
import com.example.demo.service.impl.UserServiceimpl;

@RestController
@RequestMapping("/patentes")
@CrossOrigin(origins = "http://localhost:4200")

public class PatenteController {

	@Autowired
	PatenteService patentesServiceImpl;

	@Autowired
	UserServiceimpl userService;

	@Autowired
	ParkingServiceImp parkingService;

	@Autowired
	CityService ciudadService;

	@GetMapping("/{id}")
	public ResponseEntity<Patent> getPatenteById(@PathVariable("id") long id) {

		Optional<Patent> patent = this.patentesServiceImpl.getById(id);
		if (patent.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patent>(patent.get(), HttpStatus.OK);
	}

	@PostMapping("/guardar")

	public ResponseEntity<?> savePatent(@Valid @RequestBody NewPatent newPatent, BindingResult result) {
		// CREAR UNA PATENTE
		Optional<User> user = userService.getNameUser(newPatent.getUser_name());

		System.out.println("resultado de la consulta: "
				+ patentesServiceImpl.existsByPatentAndUser(newPatent.getPatent(), user.get().getId()));
		Patent consulta = patentesServiceImpl.existsByPatentAndUser(newPatent.getPatent(), user.get().getId());

		if (consulta != null) {
			System.out.println("return patente registrada");
			return new ResponseEntity<Message>(
					new Message("La patente " + newPatent.getPatent() + " ya se encuentra registrada en su lista"),
					HttpStatus.BAD_REQUEST);
		}

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Patent pat = new Patent(newPatent.getPatent(), user.get());
		user.get().getPatenteList().add(pat);
		userService.updateUser(user.get());
		pat = this.patentesServiceImpl.guardarPatentes(pat);
		return new ResponseEntity<Patent>(pat, HttpStatus.CREATED);

	}

	@PutMapping("/{id}") // update patent
	public ResponseEntity<?> updatePatent(@Valid @RequestBody NewPatent patentDTO, BindingResult result,
			@PathVariable(value = "id") Long patentId) {
		System.out.println("Actualizando la patente " + patentDTO.getId() + patentDTO.getUser().getNameUser()
				+ patentDTO.getUser().getNameUser());

		if (result.hasErrors()) {
			return new ResponseEntity<Message>(new Message(result.getFieldError().getDefaultMessage()),
					HttpStatus.BAD_REQUEST);
		}

		Optional<Patent> patentPrevious = patentesServiceImpl.getById(patentDTO.getId());
		System.out.println("id de patente previa: " + patentPrevious.get().getPatent());
		Parking startedPatent = parkingService.findByPatentStarted(patentPrevious.get().getPatent());
		if (startedPatent != null) {
			return new ResponseEntity<Message>(
					new Message("No se puede editar esta patente porque tiene un estacionamiento iniciado"),
					HttpStatus.BAD_REQUEST);
		}

		patentPrevious.get().setPatent(patentDTO.getPatent());

		patentesServiceImpl.uptdatePatent(patentPrevious.get(), patentId);

		return new ResponseEntity<Message>(new Message("patente actualizada!"), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long patentId) {
		Optional<Patent> patent = this.patentesServiceImpl.getById(patentId);

		if (this.parkingService.parkingStartedWithPatent(patent.get().getPatent(), patent.get().getUser().getId()))
			return new ResponseEntity<Message>(
					new Message("No se puede eliminar una patente con estacionamiento iniciado"),
					HttpStatus.BAD_REQUEST);
		this.patentesServiceImpl.delete(patentId);
		return new ResponseEntity<Message>(
				new Message("Se elimino la patente " + patent.get().getPatent() + " correctamente!"), HttpStatus.OK);

	}

}
