package com.sst.paymentservice.services;

import com.razorpay.RazorpayException;
import com.sst.paymentservice.paymentgateways.PaymentGateway;
import com.sst.paymentservice.paymentgateways.RazorPayPaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentService{
    private PaymentGateway paymentGateway;

    RazorPayPaymentService(@Qualifier("Razorpay") PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String initiatePayment(Long orderId, String email) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(orderId,email);
    }
}
