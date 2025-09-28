package com.candyhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.candyhouse.model.Category;
import com.candyhouse.model.Dessert;
import com.candyhouse.model.DessertShop;
import com.candyhouse.repository.DessertRepository;
import com.candyhouse.request.CreateDessertRequest;

public class DessertServiceImp implements DessertService {
	
	@Autowired
	private DessertRepository dessertRepository;

	@Override
	public Dessert createDessert(CreateDessertRequest req, Category category, DessertShop dessertshop) {
		Dessert dessert = new Dessert();
		dessert.setDessertCategory(category);
		dessert.setDessertshop(dessertshop);
		dessert.setDescription(req.getDescription());
		dessert.setImages(req.getImages());
		dessert.setName(req.getName());
		dessert.setPrice(req.getPrice());
		dessert.setIngredient(req.getIngradients());
		dessert.setSeasonal(req.isSeasional());
		dessert.setVegatarian(req.isVegitarain());
		
		Dessert savedDessert = dessertRepository.save(dessert);
		dessertshop.getDesserts().add(savedDessert);
		
		return savedDessert;
	}

	@Override
	public void deleteDessert(Long dessertId) throws Exception {
		
		Dessert dessert = findDessertById(dessertId);
		dessert.setDessertshop(null);
		dessertRepository.save(dessert);
		
	}

	@Override
	public List<Dessert> getDessertShop(Long dessertshopId,
			boolean isVegitarain, 
			boolean isNonveg, 
			boolean isSeasonal,
			String dessertCategory) {
		
		List<Dessert> desserts = dessertRepository.findByDessertShopId(dessertshopId);
		
		if(isVegitarain) {
			desserts = filterByVegetarian(desserts, isVegitarain);
		}
		if(isNonveg) {
			desserts = filterByNonveg(desserts, isNonveg);
		}
		if(isSeasonal) {
			desserts = filterBySeasonal(desserts, isSeasonal);
		}
		if(dessertCategory != null && !dessertCategory.equals("")) {
			desserts = filterByCategory(desserts, dessertCategory);
		}
		
		return desserts;
	}

	private List<Dessert> filterByCategory(List<Dessert> desserts, String dessertCategory) {
		return desserts.stream().filter(dessert -> {
			if(dessert.getDessertCategory() != null) {
				return dessert.getDessertCategory().getName().equals(dessertCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Dessert> filterBySeasonal(List<Dessert> desserts, boolean isSeasonal) {
		return desserts.stream().filter(dessert -> dessert.isSeasonal() == isSeasonal).collect(Collectors.toList());
	}

	private List<Dessert> filterByNonveg(List<Dessert> desserts, boolean isNonveg) {
		return desserts.stream().filter(dessert -> dessert.isVegatarian() == false).collect(Collectors.toList());
	}

	private List<Dessert> filterByVegetarian(List<Dessert> desserts, boolean isVegitarain) {
		return desserts.stream().filter(dessert -> dessert.isVegatarian() == isVegitarain).collect(Collectors.toList());
	}

	@Override
	public List<Dessert> searchDessert(String keyword) {
		return dessertRepository.searchDessert(keyword);
	}

	@Override
	public Dessert findDessertById(Long dessertId) throws Exception {
		Optional<Dessert> optionalDessert = dessertRepository.findById(dessertId);
		
		if(optionalDessert.isEmpty()) {
			throw new Exception("Dessert not exist...");
		}
		
		return optionalDessert.get();
	}

	@Override
	public Dessert updateAvailabilityStatus(Long dessertId) throws Exception {
		Dessert dessert = findDessertById(dessertId);
		dessert.setAvailable(!dessert.isAvailable());
		
		return dessertRepository.save(dessert);
	}

}
