package com.candyhouse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candyhouse.dto.DessertShopDto;
import com.candyhouse.model.Address;
import com.candyhouse.model.DessertShop;
import com.candyhouse.model.User;
import com.candyhouse.repository.AddressRepository;
import com.candyhouse.repository.DessertShopRepository;
import com.candyhouse.repository.UserRepository;
import com.candyhouse.request.CreateDessertShopRequest;

@Service
public class DessertShopServiceImp implements DessertShopService{

	@Autowired
	private DessertShopRepository dessertShopRepository;
	
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public DessertShop createDessertShop(CreateDessertShopRequest req, User user) {
		
		Address address = addressRepository.save(req.getAddress());
		
		DessertShop dessertShop = new DessertShop();
		dessertShop.setAddress(address);
		dessertShop.setContactInformation(req.getContactInformation());
		dessertShop.setDescription(req.getDescription());
		dessertShop.setName(req.getName());
		dessertShop.setOpeningHours(req.getOpeningHour());
		dessertShop.setRegistrationDate(LocalDateTime.now());
		dessertShop.setOwner(user);
		
		return dessertShopRepository.save(dessertShop);
	}

	@Override
	public DessertShop updateDessertShop(Long dessertShopId, CreateDessertShopRequest updatedDessertShop)
			throws Exception {
		DessertShop dessertShop = findDessertShopById(dessertShopId);
		
		if(dessertShop.getDessertType() != null) {
			dessertShop.setDessertType(updatedDessertShop.getDessertType());
		}
		
		if(dessertShop.getDescription() != null) {
			dessertShop.setDescription(updatedDessertShop.getDescription());
		}
		
		if(dessertShop.getName() != null) {
			dessertShop.setName(updatedDessertShop.getName());
		}
		
		return dessertShopRepository.save(dessertShop);
	}

	@Override
	public void deleteDessertShop(Long DessertShopId) throws Exception {
		
		DessertShop dessertShop =  findDessertShopById(DessertShopId);
		
		dessertShopRepository.delete(dessertShop);
		
	}

	@Override
	public List<DessertShop> getAllDessertShop() {
		return dessertShopRepository.findAll();
	}

	@Override
	public List<DessertShop> searchDessertShop(String keyword) {
		return dessertShopRepository.findBySearchQuery(keyword);
	}

	@Override
	public DessertShop findDessertShopById(Long id) throws Exception {
		Optional<DessertShop> opt = dessertShopRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("DessertShop not found with id" + id);
		}
		
		return opt.get();
	}

	@Override
	public DessertShop getDessertShopByUserId(Long userId) throws Exception {
		DessertShop dessertShop = dessertShopRepository.findByOwnerId(userId);
		
		if(dessertShop == null) {
			throw new Exception("DessertShop not found with owner id" + userId);
		}
		
		return dessertShop;
	}

	@Override
	public DessertShopDto addToFavorites(Long dessertId, User user) throws Exception {
		DessertShop dessertShop = findDessertShopById(dessertId);
		
		DessertShopDto dto = new DessertShopDto();
		dto.setDescription(dessertShop.getDescription());
		dto.setImages(dessertShop.getImages());
		dto.setTitle(dessertShop.getName());
		dto.setId(dessertId);
		
		if(user.getFavorites().contains(dto)) {
			user.getFavorites().remove(dto);
		}
		
		else user.getFavorites().add(dto);
		
		userRepository.save(user);
		
		return dto;
	}

	@Override
	public DessertShop updateDessertShopStatus(Long id) throws Exception {
		DessertShop dessertShop = findDessertShopById(id);
		
		dessertShop.setOpen(!dessertShop.isOpen());
		
		return dessertShopRepository.save(dessertShop);
	}

}
