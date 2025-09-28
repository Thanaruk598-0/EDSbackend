package com.candyhouse.service;

import java.util.List;

import com.candyhouse.model.IngredientCategory;
import com.candyhouse.model.IngredientsItem;

public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name, Long dessertshopId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByDessertShopId(Long id) throws Exception;
	
	public IngredientsItem createIngredientsItem(Long dessertshopId, String ingredientName, Long categoryId) throws Exception;
	
	public List<IngredientsItem> findDessertShopIngredients(Long dessertshopId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
	
}
