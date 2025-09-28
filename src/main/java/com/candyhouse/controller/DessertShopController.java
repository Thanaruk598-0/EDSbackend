package com.candyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.candyhouse.dto.DessertShopDto;
import com.candyhouse.model.DessertShop;
import com.candyhouse.model.User;
import com.candyhouse.service.DessertShopService;
import com.candyhouse.service.UserService;

@RestController
@RequestMapping("/api/dessertshops")
public class DessertShopController {

	@Autowired
	private DessertShopService dessertShopService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public ResponseEntity<List<DessertShop>> searchDessertShop(
			@RequestHeader("Authorization") String jwt,
			@RequestParam String keyword
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		List<DessertShop> dessertShop = dessertShopService.searchDessertShop(keyword);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<DessertShop>> getAllDessertShop(
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		List<DessertShop> dessertShop = dessertShopService.getAllDessertShop();
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DessertShop> findDessertShopById(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShop dessertShop = dessertShopService.findDessertShopById(id);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/add-favorites")
	public ResponseEntity<DessertShopDto> addToFavorites(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShopDto dessertShop = dessertShopService.addToFavorites(id, user);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
	}
	
}
