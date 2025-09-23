package com.candyhouse.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DessertShop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User owner;
	
	private String name;
	
	private String description;
	
	private String cuisineType;
	
	@OneToOne
	private Address address;
	
	@Embedded
	private ContactInformation contactInformation;
	
	private String openingHours;
	
	@OneToMany(mappedBy = "dessertshop",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	@Column(length = 1000)
	private List<String> images;
	
}
