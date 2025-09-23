package com.candyhouse.service;

import service.Order;
import service.PaymentResponse;

public interface PaymentService {
	public PaymentResponse creatPatmentLink(Order order) throws StripeException;

}
