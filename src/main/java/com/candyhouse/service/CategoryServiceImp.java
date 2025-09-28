package com.candyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.candyhouse.model.Category;

@Service
public class CategoryServiceImp implements CategoryService{

	@Override
	public Category createCategory(String name, Long userId) {
		return null;
	}

	@Override
	public List<Category> findCategoryByDessertShopId(Long id) throws Exception {
		return null;
	}

	@Override
	public Category findCatogoryById(Long id) throws Exception {
		return null;
	}

}
