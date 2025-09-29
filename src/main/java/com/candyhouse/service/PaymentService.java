package com.candyhouse.service;
import com.candyhouse.response.PaymentResponse;
import service.Order;
import service.PaymentResponse;

@Data
public interface PaymentService {
	public PaymentResponse creatPatmentLink(Order order) throws StripeException;

	
	
	
}
