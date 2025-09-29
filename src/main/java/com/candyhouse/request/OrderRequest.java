package com.candyhouse.request;

import com.candyhouse.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long dessertshopId;
	private Address deliveryAddress;
	
}
