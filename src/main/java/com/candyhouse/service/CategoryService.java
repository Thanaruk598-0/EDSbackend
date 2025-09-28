package com.candyhouse.service;

import java.util.List;

import com.candyhouse.model.Category;

public interface CategoryService {
	
	public Category createCategory(String name, Long userId);
	
	public List<Category> findCategoryByDessertShopId(Long id) throws Exception;
	
	public Category findCatogoryById(Long id) throws Exception;
	
}
