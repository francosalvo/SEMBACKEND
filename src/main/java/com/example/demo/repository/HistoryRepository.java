package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.History;

public interface HistoryRepository extends CrudRepository<History, Long> {

	@Query(value = "SELECT * FROM History h WHERE h.current_account_id =?1 ORDER BY h.date_transaction desc", nativeQuery = true)
	List<History> getByCurrentAccountId(Long id);

}
