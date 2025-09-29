package com.candyhouse.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candyhouse.model.Cart;
import com.candyhouse.model.CartItem;
import com.candyhouse.model.Dessert;
import com.candyhouse.model.User;
import com.candyhouse.repository.CartItemRepository;
import com.candyhouse.repository.CartRepository;
import com.candyhouse.request.AddCartItemRequest;

@Service
public class CartServiceImp implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private DessertService dessertService;

	@Override
	public CartItem addItemCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		Dessert dessert = dessertService.findDessertById(req.getDessertId());
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItem()) {
			if(cartItem.getDessert().equals(dessert)) {
				int newQuantity = cartItem.getQuantity() + req.getQuantity();
				return updateCartitemQuantity(cartItem.getId(), newQuantity);
			}
		}
		
		CartItem newCartItem = new CartItem();
		newCartItem.setDessert(dessert);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity() * dessert.getPrice());
		
		CartItem savedCartItem = cartItemRepository.save(newCartItem);
		
		cart.getItem().add(savedCartItem);
		
		return savedCartItem;
	}

	@Override
	public CartItem updateCartitemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		
		CartItem item = cartItemOptional.get();
		item.setQuantity(quantity);
		
		item.setTotalPrice(item.getDessert().getPrice() * quantity);
		
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		
		if(cartItemOptional.isEmpty()) {
			throw new Exception("cart item not found");
		}
		
		CartItem item = cartItemOptional.get();
		
		cart.getItem().remove(item);
		
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		
		Long total = 0L;
		
		for(CartItem cartItem : cart.getItem()) {
			total += cartItem.getDessert().getPrice() * cartItem.getQuantity();
		}
		
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		
		Optional<Cart> optionalCart = cartRepository.findById(id);
		
		if(optionalCart.isEmpty()) {
			throw new Exception("cart not found with id " + id);
		}
		
		return optionalCart.get();
	}

	@Override
	public Cart findCartByUserId(String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		return cartRepository.findByCustomerId(user.getId());
	}

	@Override
	public Cart clearCart(String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Cart cart = findCartByUserId(jwt);
		
		cart.getItem().clear();
		
		return cartRepository.save(cart);
	}

}
