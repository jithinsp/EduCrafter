package com.corner.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetails {
    private String orderId;
    private String currency;
    private Integer amount;
    private String key;
}
