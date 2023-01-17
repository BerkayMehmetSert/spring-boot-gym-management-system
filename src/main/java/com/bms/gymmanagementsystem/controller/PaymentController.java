package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.ClientDto;
import com.bms.gymmanagementsystem.dto.PaymentDto;
import com.bms.gymmanagementsystem.request.payment.CreatePaymentRequest;
import com.bms.gymmanagementsystem.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createPayment(@RequestBody CreatePaymentRequest request) {
        service.createPayment(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<Void> updatePaymentAmount(@PathVariable String id,
                                                    @RequestParam Double amount) {
        service.updatePaymentAmount(id, amount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        service.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Set<PaymentDto>> findPaymentByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(service.findPaymentByClientId(clientId));
    }

    @GetMapping
    public ResponseEntity<Set<PaymentDto>> findAllPayments() {
        return ResponseEntity.ok(service.findAllPayments());
    }
}
