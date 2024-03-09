package com.corner.finance.service;

import com.corner.finance.dto.PaymentRequest;
import com.corner.finance.dto.TransactionDetails;
import com.corner.finance.entity.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    public TransactionDetails createTransaction(float amount);
    public boolean verifyPayment(String orderId, String paymentId);

    void paymentSuccess(PaymentRequest paymentRequest);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByUser(String username);
}
