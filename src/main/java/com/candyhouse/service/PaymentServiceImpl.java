package service;

import com.candyhouse.service.Order;
import com.candyhouse.service.PaymentResponse;
import com.candyhouse.service.PaymentService;
import com.candyhouse.strip.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

@Service
import jakarta.websocket.Session;
public class PaymentServiceImpl implements PaymentService{
	
	@Value("${stripe.api.key}")
	private String StripeSecretKey;

	@Override
	public PaymentResponse creatPatmentLink(Order order) {
		
		Stripe.apiKey=StripeSecretKey;
		
		SessionCreateParam params = SessionCreateParams.buider().addPaymentMethodType
				(SessionCreateParams.
				PaymentMethodType.CARD).setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("//ใส่localhost +order.getId()")
				.setCancelUrl("//ใส่localhost +order.getId()")
				.addLineItem(SessionCreateParams.LineItem.builder()
				.setQuantity(1L).setPrinceData(SessionCreateParams.LineItem.PrinceData.builder()
					.setCurrency("usd"))
					.setUnitAmount((long)order.getTotalPrice()*100)
					.setProductData(SeccionCreateParams.LineItem.PriceData.ProductData.builder()
					.setName("sweetopia")
					.build())
				)
				.build()
				)
				.build();
				
				Session session = Session.create(params);
				
				PaymentResponse res = new PaymentResponse();
				res.setPayment_url(session.getUrl());
				
		return res;
	}

}
