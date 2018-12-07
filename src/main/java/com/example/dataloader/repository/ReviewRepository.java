package com.example.dataloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataloader.resource.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
}
