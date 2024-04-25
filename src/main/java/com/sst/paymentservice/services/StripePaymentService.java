package com.sst.paymentservice.services;

import com.razorpay.RazorpayException;
import com.sst.paymentservice.paymentgateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService{
    private PaymentGateway paymentGateway;

    StripePaymentService(@Qualifier("Stripe") PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    @Override
    public String initiatePayment(Long orderId, String email) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(orderId,email);
    }
}
