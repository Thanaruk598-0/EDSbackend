package com.candyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candyhouse.model.Dessert;
import com.candyhouse.model.DessertShop;
import com.candyhouse.model.User;
import com.candyhouse.request.CreateDessertRequest;
import com.candyhouse.response.MessageResponse;
import com.candyhouse.service.DessertService;
import com.candyhouse.service.DessertShopService;
import com.candyhouse.service.UserService;

@RestController
@RequestMapping("/api/admin/dessert")
public class AdminDessertController {
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DessertShopService dessertshopService;
	
	@PostMapping
	public ResponseEntity<Dessert> createDessert(@RequestBody CreateDessertRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		DessertShop dessertshop = dessertshopService.findDessertShopById(req.getDessertshopId());
		Dessert dessert = dessertService.createDessert(req, req.getCategory(), dessertshop);
		
		return new ResponseEntity<>(dessert, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteDessert(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		dessertService.deleteDessert(id);
		
		MessageResponse res = new MessageResponse();
		res.setMessage("dessert deleted successfully");
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Dessert> updateDessertAvailabilityStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Dessert dessert = dessertService.updateAvailabilityStatus(id);
		
		return new ResponseEntity<>(dessert, HttpStatus.CREATED);
		
	}

}
