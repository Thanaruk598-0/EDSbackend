package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.candyhouse.model.DessertShop;

public interface DessertShopRepository extends JpaRepository<DessertShop, Long>{
	
	@Query("SELECT r FROM DessertShop r WHERE lower(r.name) LIKE lower(concat('%',:query,'%')) "
			+ "OR lower(r.dessertType) LIKE lower(concat('%',:query,'%'))")
	List<DessertShop> findBySearchQuery(String query);
	DessertShop findByOwnerId(Long userId);
	
}
