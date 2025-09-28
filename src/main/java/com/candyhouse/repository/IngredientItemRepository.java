package com.candyhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
	
	List<IngredientsItem> findByDessertshop_Id(Long id);

}
