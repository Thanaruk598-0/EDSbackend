package com.candyhouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candyhouse.model.Category;
import com.candyhouse.model.DessertShop;
import com.candyhouse.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private DessertShopService dessertShopService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		DessertShop dessertShop = dessertShopService.getDessertShopByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setDessertshop(dessertShop);
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByDessertShopId(Long id) throws Exception {
		DessertShop dessertShop = dessertShopService.getDessertShopByUserId(id);
		
		return categoryRepository.findByDessertshop_Id(id);
	}

	@Override
	public Category findCatogoryById(Long id) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		if(optionalCategory.isEmpty()) {
			throw new Exception("category not found");
		}
		
		return optionalCategory.get();
	}

}
