package com.example.dataloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.resource.Country;

public interface CountryRepository extends JpaRepository<Country, String>{

}
