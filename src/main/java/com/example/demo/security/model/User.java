package com.example.demo.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.entity.CurrentAccount;
import com.example.demo.entity.Parking;
import com.example.demo.entity.Patent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Column(unique = true)
	private String nameUser; // Nombre de usuario despues lo voy a reemplazar con telefono
	@NotNull
	private String email;
	@NotNull
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> rol = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	Set<Patent> patentlist;

	@JsonIgnore
	@OneToOne
	private Parking parking;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private CurrentAccount currentAccount;

	public User() {
	}

	public User(@NotNull String name, @NotNull String nameUser, @NotNull String email,
			@NotNull String password) {
		super();
		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public Set<Patent> getPatenteList() {
		return patentlist;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return rol;
	}

	public void setRoles(Set<Rol> rol) {
		this.rol = rol;
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

}
