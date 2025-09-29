package com.candyhouse.service;

import java.util.List;

import com.candyhouse.model.Order;
import com.candyhouse.model.User;
import com.candyhouse.request.OrderRequest;

public interface OrderService {
	
	public Order createOrder(OrderRequest order, User user) throws Exception;
	
	public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getDessertShopOrder(Long dessertshopId, String orderStatus) throws Exception;
	
	public Order findOrderById(Long orderId) throws Exception;

}
