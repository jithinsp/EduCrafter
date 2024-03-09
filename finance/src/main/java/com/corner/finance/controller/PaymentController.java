package com.corner.finance.controller;

import com.corner.finance.dto.PaymentRequest;
import com.corner.finance.dto.TransactionDetails;
import com.corner.finance.entity.Payment;
import com.corner.finance.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("finance/payment/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("pay/{amount}")
    public ResponseEntity<?> payOnline(@PathVariable Float amount){
        TransactionDetails transactionDetails =paymentService.createTransaction(amount);
        return ResponseEntity.ok().body(transactionDetails);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        List<Payment> paymentList = paymentService.getAllPayments();
        return ResponseEntity.ok().body(paymentList);
    }

    @GetMapping("get/{username}")
    public ResponseEntity<?> get(@PathVariable String username){
        List<Payment> paymentList = paymentService.getPaymentsByUser(username);
        return ResponseEntity.ok().body(paymentList);
    }

    @PostMapping("pay")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            System.out.println(paymentRequest);
            paymentService.paymentSuccess(paymentRequest);
            return ResponseEntity.ok("Payment processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
