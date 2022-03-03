package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CurrentAccount;
import com.example.demo.repository.CurrentAccountRepository;
import com.example.demo.service.CurrentAccounService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/c_corriente")
public class CuentaController {

	@Autowired
	private CurrentAccounService cuentaCorrienteServiceImpl;

	CurrentAccountRepository cuentaCorrienteRepository;

	@GetMapping
	@RequestMapping(value = "getCuentaCorriente", method = RequestMethod.GET)
	public ArrayList<CurrentAccount> CurrentAccount() {

		return (ArrayList<CurrentAccount>) cuentaCorrienteRepository.findAll();

	}

	// REQUEST BODY, O SEA, TODOS LAS PATENTES PUEDEN ENVIAR INFO DENTRO DE LA
	// SOLICITUD HTTP

	@PostMapping()
	public CurrentAccount saveCurrentAccount(@RequestBody CurrentAccount cuentaCorriente) {
		return this.cuentaCorrienteServiceImpl.save(cuentaCorriente);
	}

	@PutMapping
	@RequestMapping(value = "actualizarCuentaCorriente", method = RequestMethod.PUT)
	public ResponseEntity<CurrentAccount> updateCurrentAccount(@RequestBody CurrentAccount cuentaCorriente) {

		CurrentAccount cuentaCorrienteActualizada = this.cuentaCorrienteServiceImpl.update(cuentaCorriente);

		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaCorrienteActualizada);

	}

	@DeleteMapping
	@RequestMapping(value = "eliminarCuentaCorriente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CurrentAccount> deleteCurrentAccoung(@PathVariable Long id) {

		this.cuentaCorrienteServiceImpl.delete(id);

		return ResponseEntity.ok().build();

	}

}
