package com.candyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candyhouse.model.IngredientCategory;
import com.candyhouse.model.IngredientsItem;
import com.candyhouse.request.IngredientCategoryRequest;
import com.candyhouse.request.IngredientRequest;
import com.candyhouse.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

	@Autowired
	private IngredientsService ingredientsService;
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(
			@RequestBody IngredientCategoryRequest req) throws Exception {
		
		 IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getDessertshopId());
		 
		 return new ResponseEntity<>(item, HttpStatus.CREATED);
		
	}
	
	@PostMapping()
	public ResponseEntity<IngredientsItem> createIngredientItem(
			@RequestBody IngredientRequest req) throws Exception {
		
		 IngredientsItem item = ingredientsService.createIngredientsItem(req.getDessertshopId(), req.getName(), req.getCategoryId());
		 
		 return new ResponseEntity<>(item, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}/stoke")
	public ResponseEntity<IngredientsItem> updateIngredientStock(
			@PathVariable Long id) throws Exception {
		
		 IngredientsItem item = ingredientsService.updateStock(id);
		 
		 return new ResponseEntity<>(item, HttpStatus.OK);
		
	}
	
	@GetMapping("/dessertshop/{id}")
	public ResponseEntity<List<IngredientsItem>> getDessertShopIngredient(
			@PathVariable Long id) throws Exception {
		
		 List<IngredientsItem> items = ingredientsService.findDessertShopIngredients(id);
		 
		 return new ResponseEntity<>(items, HttpStatus.OK);
		
	}
	
	@GetMapping("/dessertshop/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getDessertShopIngredientCategory(
			@PathVariable Long id) throws Exception {
		
		 List<IngredientCategory> items = ingredientsService.findIngredientCategoryByDessertShopId(id);
		 
		 return new ResponseEntity<>(items, HttpStatus.OK);
		
	}
	
}
