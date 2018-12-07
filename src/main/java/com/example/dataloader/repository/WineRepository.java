package com.example.dataloader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.resource.Wine;

public interface WineRepository extends JpaRepository<Wine, String>{
	
	List<Wine> findByWineName(String wineName);

}
