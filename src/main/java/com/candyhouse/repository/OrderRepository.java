package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findByCustomerId(Long userId);
	
	public List<Order> findByDessertShop_Id(Long dessertShopId);
	
}