package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.example.demo.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "patente")
public class Patent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "patente")
	@Pattern(regexp = "([a-zA-Z]{3}\\d{3})|([a-zA-Z]{2}\\d{3}[a-zA-Z]{2})", message = "la patente debe tener el formato AAA999 o AA000AA")
	private String patent;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_patenteuser")
	User user;

	public Patent(String patente, User user) {
		super();
		this.patent = patente;
		this.user = user;
	}

	public Patent() {
		super();
	}

}
