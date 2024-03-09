package com.corner.finance.service;

import com.corner.finance.dto.PaymentRequest;
import com.corner.finance.dto.TransactionDetails;
import com.corner.finance.entity.Payment;
import com.corner.finance.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    private static final String KEY= "rzp_test_t9SlpRT3SqMgi3";

    private static final String KEY_SECRET= "jc7yuKuzCaRJ8AVsKR3uZGEt";

    private static final String CURRENCY= "INR";

    public TransactionDetails createTransaction(float amount){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100));//to convert paise to rupee
            jsonObject.put("currency",CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

            Order order = razorpayClient.orders.create(jsonObject);
            return prepareTransactionDetails(order);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");
        TransactionDetails transactionDetails = new TransactionDetails(orderId,currency,amount,KEY);
        return transactionDetails;
    }

    public boolean verifyPayment(String orderId, String paymentId) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

            JSONObject payload = new JSONObject();
            payload.put("order_id", orderId);
            payload.put("payment_id", paymentId);

            // Verify the signature
            boolean isValidSignature = Utils.verifyPaymentSignature(payload, KEY_SECRET);
            return isValidSignature;
        } catch (Exception e) {
            // Handle exception, log error, etc.
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void paymentSuccess(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setRazorpay_payment_id(paymentRequest.getRazorpay_payment_id());
        payment.setAmount(paymentRequest.getAmount()/100);
        payment.setUsername(paymentRequest.getUsername());
        paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByUser(String username) {
        return paymentRepository.findAllByUsername(username);
    }
}
