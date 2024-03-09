package com.corner.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private String orderId;
    private String razorpay_payment_id;
    private String username;
    private Float amount;
    private String currency;
    private String key;
}
