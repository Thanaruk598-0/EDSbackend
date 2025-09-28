package com.candyhouse.request;

import java.util.List;

import com.candyhouse.model.Category;
import com.candyhouse.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateDessertRequest {
	
	private String name;
	private String description;
	private Long price;
	private Category category;
	private List<String> images;
	private Long dessertshopId;
	private boolean Vegitarain;
	private boolean seasional;
	private List<IngredientsItem> ingradients;
	
}
