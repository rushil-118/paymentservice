package com.sst.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
//import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Stripe")
public class StripePaymentGateway implements PaymentGateway{
    @Value("${stripe.api.key}")
    private String stripeapikey;

    @Override
    public String generatePaymentLink(Long orderId, String email) throws RazorpayException, StripeException {
        /*
            //Integrating Stripe PaymentGateway\\

            First get the necessary stripe api key from following link - "https://dashboard.stripe.com/test/apikeys"
            get test cards and other testing details from the following link - "https://docs.stripe.com/testing"
            TO create a payment link follow the steps from the following link - "https://docs.stripe.com/api/payment_links/payment_links/object"
            but to create a payment link we require a price object
            To create a price object follow the steps from the following link - "https://docs.stripe.com/api/prices/object"

            For the callback url, we gotta add setAfterCompletion in the paymentLink creation part
            you can get more details in the following link - "https://docs.stripe.com/payment-links/api"
         */

        Stripe.apiKey = stripeapikey;

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("iPhone 15").build()
                        )
                        .build();
        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) // Callback
                                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://www.youtube.com/")
                                                .build()
                                        )
                                        .build()

                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.toString();
    }
}
