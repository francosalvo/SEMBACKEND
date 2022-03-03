package com.example.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.demo.security.dto.NewUser;

import lombok.Data;

@Data

public class NewPatent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String user_name;
	private Long id;

	@NotBlank
	@Pattern(regexp = "([a-zA-Z]{3}\\d{3})|([a-zA-Z]{2}\\d{3}[a-zA-Z]{2})", message = "la patente debe tener el formato AAA999 o AA000AA")
	private String patent;

	private NewUser user;

	public NewPatent(String user_name, String patent) {
		super();
		this.user_name = user_name;
		this.patent = patent;
	}

	public NewUser getUser() {
		return user;
	}

	public void setUser(NewUser user) {
		this.user = user;
	}

	public void setNumber(String number) {
		this.patent = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
