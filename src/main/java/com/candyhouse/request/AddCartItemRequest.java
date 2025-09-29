package com.candyhouse.request;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemRequest {

	private Long dessertId;
	private int quantity;
	private List<String> ingredients;
	
}
