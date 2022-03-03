package com.example.demo.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Patent;

@Service
public interface PatenteService {

    Patent getPatentByIdUser(Patent patent);

    Patent guardarPatentes(Patent patent);

    Patent uptdatePatent(Patent patent, Long id);

    Patent existsByPatentAndUser(String patente, Long idUser);

    Optional<Patent> getById(Long id);

    Set<Patent> getByIdUser(Long id);

    boolean delete(Long id);

}
