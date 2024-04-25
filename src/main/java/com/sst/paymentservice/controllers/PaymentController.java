package com.sst.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.sst.paymentservice.DTOS.InitiatePaymentRequestDTO;
import com.sst.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(@Qualifier("stripe") PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDTO requestDTO) throws RazorpayException, StripeException {
        return paymentService.initiatePayment(requestDTO.getOrderId(), requestDTO.getEmail());
    }
}
