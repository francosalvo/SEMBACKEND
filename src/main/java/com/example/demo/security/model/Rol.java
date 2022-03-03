package com.example.demo.security.model;

import javax.persistence.*;

import com.example.demo.security.enums.RolName;
import com.sun.istack.NotNull;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolName rolName;

	public Rol() {
		super();
	}

	public Rol(RolName rolName) {
		super();
		this.rolName = rolName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolName getRolName() {
		return rolName;
	}

	public void setRolNombre(@NotNull RolName rolName) {
		this.rolName = rolName;
	}

}
