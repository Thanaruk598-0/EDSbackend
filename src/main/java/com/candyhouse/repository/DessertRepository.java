package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyhouse.model.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long>{

	List<Dessert> findByDessertShopId(Long dessertId);
	
	@Query("SELECT f FROM Dessert f WHERE f.name LIKE %:keyword% OR f.dessertCategory.name LIKE %:keyword%")
	List<Dessert> searchDessert(@Param("keyword") String keyword);
	
}
