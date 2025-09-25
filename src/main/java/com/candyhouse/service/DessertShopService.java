package com.candyhouse.service;

import java.util.List;

import com.candyhouse.dto.DessertShopDto;
import com.candyhouse.model.DessertShop;
import com.candyhouse.model.User;
import com.candyhouse.request.CreateDessertShopRequest;

public interface DessertShopService {

	public DessertShop createDessertShop(CreateDessertShopRequest req, User user);
	
	public DessertShop updateDessertShop(Long DessertShopId, CreateDessertShopRequest updatedDesssertShop) throws Exception;
	
	public void deleteDessertShop(Long DessertShopId) throws Exception;
	
	public List<DessertShop> getAllDessertShop();
	
	public List<DessertShop> searchDessertShop(String keyword);
	
	public DessertShop findDessertShopById(Long id) throws Exception;
	
	public DessertShop getDessertShopByUserId(Long userId) throws Exception;
	
	public DessertShopDto addToFavorites(Long dessertId, User user) throws Exception;
	
	public DessertShop updateDessertShopStatus(Long id) throws Exception;
	
}
