package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findByDessertShopId(Long id);
	
}
