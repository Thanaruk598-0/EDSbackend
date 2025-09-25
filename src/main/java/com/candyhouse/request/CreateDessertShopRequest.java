package com.candyhouse.request;

import java.util.List;

import com.candyhouse.model.Address;
import com.candyhouse.model.ContactInformation;

import lombok.Data;

@Data
public class CreateDessertShopRequest {

	private Long id;
	private String name;
	private String description;
	private String dessertType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHour;
	private List<String> images;
	
}
