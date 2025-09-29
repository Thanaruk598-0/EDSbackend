package com.candyhouse.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candyhouse.model.Address;
import com.candyhouse.model.Cart;
import com.candyhouse.model.CartItem;
import com.candyhouse.model.DessertShop;
import com.candyhouse.model.Order;
import com.candyhouse.model.OrderItem;
import com.candyhouse.model.User;
import com.candyhouse.repository.AddressRepository;
import com.candyhouse.repository.OrderItemRepository;
import com.candyhouse.repository.OrderRepository;
import com.candyhouse.repository.UserRepository;
import com.candyhouse.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DessertShopService dessertShopService;
	
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		
		Address shipAddress = order.getDeliveryAddress();
		
		Address savedAddress = addressRepository.save(shipAddress);
		
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		DessertShop dessertshop = dessertShopService.findDessertShopById(order.getDessertshopId());
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setOrderStatus("Pending");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setDessertshop(dessertshop);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem : cart.getItem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setDessert(cartItem.getDessert());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long totalPrice = cartService.calculateCartTotals(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
		
		dessertshop.getOrders().add(savedOrder);
		
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		
		Order order = findOrderById(orderId);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
			
		}
		
		throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		
		Order order = findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getDessertShopOrder(Long dessertshopId, String orderStatus) throws Exception {
		List<Order> orders =  orderRepository.findByDessertShopId(dessertshopId);
		
		if(orderStatus != null) {
			orders = orders.stream().filter(order -> 
			order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		
		return orders;
		
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		
		if(optionalOrder.isEmpty()) {
			throw new Exception("order not found");
		}
		
		return optionalOrder.get();
	}

}
