package com.sst.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    String initiatePayment(Long orderId, String email) throws RazorpayException, StripeException;
}
