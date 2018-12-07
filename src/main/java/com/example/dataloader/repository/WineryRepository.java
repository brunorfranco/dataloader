package com.example.dataloader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.resource.Winery;

public interface WineryRepository extends JpaRepository<Winery, Integer>{

	List<Winery> findByName(String name);
	
}
