package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MembershipRepository extends JpaRepository<Membership, String> {
    Set<Membership> findByClient_Id(String id);
    Set<Membership> findByStatus(String status);
}