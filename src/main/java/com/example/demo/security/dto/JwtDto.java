package com.example.demo.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String nameUser;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nameUser, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nameUser = nameUser;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNombreUsuario() {
        return nameUser;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nameUser = nombreUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
