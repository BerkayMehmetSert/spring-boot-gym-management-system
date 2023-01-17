package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}