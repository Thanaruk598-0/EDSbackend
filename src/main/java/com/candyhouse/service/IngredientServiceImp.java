package com.candyhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candyhouse.model.DessertShop;
import com.candyhouse.model.IngredientCategory;
import com.candyhouse.model.IngredientsItem;
import com.candyhouse.repository.IngredientCategoryRepository;
import com.candyhouse.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService{
	
	@Autowired
	private IngredientItemRepository ingredientItemRepository;
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	
	@Autowired
	private DessertShopService dessertShopService;

	@Override
	public IngredientCategory createIngredientCategory(String name, Long dessertshopId) throws Exception {
		
		DessertShop dessertShop = dessertShopService.findDessertShopById(dessertshopId);
		
		IngredientCategory category = new IngredientCategory();
		category.setDessertshop(dessertShop);
		category.setName(name);
		
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		
		Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("ingredient category not found");
		}
		
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByDessertShopId(Long id) throws Exception {
		
		dessertShopService.findDessertShopById(id);
		
		return ingredientCategoryRepository.findByDessertshop_Id(id);
	}

	@Override
	public IngredientsItem createIngredientsItem(Long dessertshopId, String ingredientName, Long categoryId)
			throws Exception {
		
		DessertShop dessertShop = dessertShopService.findDessertShopById(dessertshopId);
		
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setDessertshop(dessertShop);
		item.setCategory(category);
		
		IngredientsItem ingredient = ingredientItemRepository.save(item);
		category.getIngredients().add(ingredient);
		
		return ingredient;
	}

	@Override
	public List<IngredientsItem> findDessertShopIngredients(Long dessertshopId) {
		return ingredientItemRepository.findByDessertshop_Id(dessertshopId);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		
		Optional<IngredientsItem> optionalIngradientsItem = ingredientItemRepository.findById(id);
		
		if(optionalIngradientsItem.isEmpty()) {
			throw new Exception("ingredient not found");
		}
		
		IngredientsItem ingredientsItem = optionalIngradientsItem.get();
		ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
		
		return ingredientItemRepository.save(ingredientsItem);
	}

}
