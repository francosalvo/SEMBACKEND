package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patent;

@Repository
public interface PatentRepository extends CrudRepository<Patent, Long> {

	@Query(value = "SELECT * FROM patente p WHERE p.patente=?1 and p.id_patenteuser =?2", nativeQuery = true)

	Patent existsByPatenteAndUsuario(String patente, Long idUser);

	@Query(value = "SELECT * FROM patente p WHERE p.id_patenteuser =?1", nativeQuery = true)
	Set<Patent> getByIdUser(Long idUser);

}
