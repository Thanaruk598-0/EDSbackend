package com.candyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candyhouse.model.DessertShop;
import com.candyhouse.model.User;
import com.candyhouse.request.CreateDessertShopRequest;
import com.candyhouse.response.MessageResponse;
import com.candyhouse.service.DessertShopService;
import com.candyhouse.service.UserService;

@RestController
@RequestMapping("/api/admin/dessertShop")
public class AdminDessertShopController {

	@Autowired
	private DessertShopService dessertShopService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<DessertShop> createdDessertShop(
			@RequestBody CreateDessertShopRequest req,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShop dessertShop = dessertShopService.createDessertShop(req, user);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.CREATED);
			}
	
	@PutMapping("/{id}")
	public ResponseEntity<DessertShop> updateDessertShop(
			@RequestBody CreateDessertShopRequest req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShop dessertShop = dessertShopService.updateDessertShop(id, req);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.CREATED);
			}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteDessertShop(
			@RequestBody CreateDessertShopRequest req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		dessertShopService.deleteDessertShop(id);
		
		MessageResponse res = new MessageResponse();
		
		res.setMessage("DessertShop delete Successfully");
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
			}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<DessertShop> updateDessertShopStatus(
			@RequestBody CreateDessertShopRequest req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShop dessertShop = dessertShopService.updateDessertShopStatus(id);
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
			}
	
	@GetMapping("/user")
	public ResponseEntity<DessertShop> findDessertShopByUserId(
			@RequestBody CreateDessertShopRequest req,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		DessertShop dessertShop = dessertShopService.getDessertShopByUserId(user.getId());
		
		return new ResponseEntity<>(dessertShop, HttpStatus.OK);
			}
	
}
