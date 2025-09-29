package com.candyhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
