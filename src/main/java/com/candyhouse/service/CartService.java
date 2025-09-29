package com.candyhouse.service;

import com.candyhouse.model.Cart;
import com.candyhouse.model.CartItem;
import com.candyhouse.request.AddCartItemRequest;

public interface CartService {

	public CartItem addItemCart(AddCartItemRequest req, String jwt)throws Exception;
	
	public CartItem updateCartitemQuantity(Long cartItemId, int quantity) throws Exception;
	
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
	
	public Long calculateCartTotals(Cart cart) throws Exception;
	
	public Cart findCartById(Long id) throws Exception;
	
	public Cart findCartByUserId(String jwt) throws Exception;
	
	public Cart clearCart(String jwt) throws Exception;
	
}
