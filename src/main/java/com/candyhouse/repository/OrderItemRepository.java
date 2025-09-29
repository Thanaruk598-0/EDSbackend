package com.candyhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
