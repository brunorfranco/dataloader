package com.example.dataloader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.resource.Taster;

public interface TasterRepository extends JpaRepository<Taster, Integer>{

	List<Taster> findByTwitterhandletaster(String twitterHandle);
	
}
