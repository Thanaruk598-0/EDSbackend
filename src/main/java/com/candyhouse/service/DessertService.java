package com.candyhouse.service;

import java.util.List;

import com.candyhouse.model.Category;
import com.candyhouse.model.Dessert;
import com.candyhouse.model.DessertShop;
import com.candyhouse.request.CreateDessertRequest;

public interface DessertService {
	
	public Dessert createDessert(CreateDessertRequest req, Category category,DessertShop dessertshop);
	
	void deleteDessert(Long dessertId) throws Exception;
	
	public List<Dessert> getDessertShop(Long dessertshopId,
			boolean isVegitarain, 
			boolean isNonveg, 
			boolean isSeasonal, 
			String dessertCategory);
	
	public List<Dessert> searchDessert(String keyword);
	public Dessert findDessertById(Long dessertId) throws Exception;
	public Dessert updateAvailabilityStatus(Long dessertId) throws Exception;
	
}
