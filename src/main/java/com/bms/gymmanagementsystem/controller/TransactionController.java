package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.TransactionDto;
import com.bms.gymmanagementsystem.request.transaction.CreateTransactionRequest;
import com.bms.gymmanagementsystem.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionRequest request) {
        service.createTransaction(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> updateTransactionName(@PathVariable String id, @RequestParam String name) {
        service.updateTransactionName(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        service.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<TransactionDto>> findAllTransactions() {
        return ResponseEntity.ok(service.findAllTransactions());
    }
}
