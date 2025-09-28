package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.candyhouse.model.DessertShop;

public interface DessertShopRepository extends JpaRepository<DessertShop, Long>{
	
	@Query("SELECT d FROM DessertShop d WHERE lower(d.name) LIKE lower(concat('%',:query,'%')) "
			+ "OR lower(d.dessertType) LIKE lower(concat('%',:query,'%'))")
	List<DessertShop> findBySearchQuery(String query);
	DessertShop findByOwnerId(Long userId);
	
}
