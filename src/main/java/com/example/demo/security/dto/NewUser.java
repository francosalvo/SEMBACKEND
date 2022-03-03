package com.example.demo.security.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    private String nameUser; // telefono

    @NotBlank(message = "el campo NO puede estar vacio")
    @Email(message = "el correo debe tener el formato texto@texto.texto")
    private String email;

    @NotBlank
    @NotNull(message = "Debe ingresar una clave")
    private String password;

    private Set<String> rol = new HashSet<>();

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

    public Set<String> getRol() {
        return rol;
    }

    public void setRol(Set<String> rol) {
        this.rol = rol;
    }
}
