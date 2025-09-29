package com.candyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.candyhouse.model.Dessert;
import com.candyhouse.model.User;
import com.candyhouse.service.DessertService;
import com.candyhouse.service.DessertShopService;
import com.candyhouse.service.UserService;

@RestController
@RequestMapping("/api/dessert")
public class DessertController {
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DessertShopService dessertshopService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Dessert>> searchDessert(@RequestParam String name,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);

		List<Dessert> dessert = dessertService.searchDessert(name);
		
		return new ResponseEntity<>(dessert, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/dessertshop/{dessertshopId}")
	public ResponseEntity<List<Dessert>> getDessertShopDessert(
			@RequestParam(required = false) boolean vagetarian,
			@RequestParam(required = false) boolean seasonal,
			@RequestParam(required = false) boolean nonveg,
			@RequestParam(required = false) String dessert_category,
			@PathVariable Long dessertshopId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);

		List<Dessert> dessert = dessertService.getDessertShop(dessertshopId, vagetarian, nonveg, seasonal, dessert_category);
		
		return new ResponseEntity<>(dessert, HttpStatus.OK);
		
	}
	
}
