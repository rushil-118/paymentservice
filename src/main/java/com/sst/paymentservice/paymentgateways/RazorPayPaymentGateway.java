package com.sst.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component("Razorpay")
public class RazorPayPaymentGateway implements PaymentGateway{
    @Value("${razorpay.key.id}")
    private String razorpayKeyID;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    @Override
    public String generatePaymentLink(Long orderId, String email) throws RazorpayException {
        /*
            //Integrating the Razorpay payment gateway\\

            Follow the steps given in the following link - "https://razorpay.com/docs/api/payments/payment-links/create-standard/";
            and we can create a paymentLinkREQUEST

            Make sure to get the razorpayKeyId and razorpayKeySecret from the razorpay api keys, which
            can be found in the following link - "https://dashboard.razorpay.com/app/website-app-settings/api-keys"

            Change the timestamp to minimum 15-30 minutes later time.
            you can change the callback url at your will.

         */

        RazorpayClient razorpay = new RazorpayClient(razorpayKeyID, razorpayKeySecret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        //paymentLinkRequest.put("accept_partial",true);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1714065947);
        paymentLinkRequest.put("reference_id","TS1989");
        paymentLinkRequest.put("description","Sample payment link");
        JSONObject customer = new JSONObject();
        customer.put("name","BOT");
        customer.put("contact","+919000090000");
        customer.put("email",email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://youtube.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

        return payment.toString();
    }
}
