package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyhouse.model.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long>{

	List<Dessert> findByDessertShop_Id(Long dessertShopId);
	
	@Query("SELECT d FROM Dessert d WHERE d.name LIKE %:keyword% OR d.DessertCategory.name LIKE %:keyword%")
	List<Dessert> searchDessert(@Param("keyword") String keyword);
	
}
