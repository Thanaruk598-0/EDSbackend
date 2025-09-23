package com.candyhouse.dto;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DessertShopDto {

	private String title;
	
	@Column(length = 1000)
	private List<String> images;
	
	private String description;
	private Long id;
	
}
