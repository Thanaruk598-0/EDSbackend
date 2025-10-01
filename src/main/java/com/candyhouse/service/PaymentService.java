package com.candyhouse.service;

import com.candyhouse.model.Order;
import com.candyhouse.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	public PaymentResponse createPaymentLink(Order order) throws StripeException;

}