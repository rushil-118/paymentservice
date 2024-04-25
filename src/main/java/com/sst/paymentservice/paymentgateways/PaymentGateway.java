package com.sst.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(Long orderId, String email) throws RazorpayException, StripeException;
}
