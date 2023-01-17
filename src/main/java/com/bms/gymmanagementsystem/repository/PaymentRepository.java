package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Set<Payment> findByClient_Id(String id);
}