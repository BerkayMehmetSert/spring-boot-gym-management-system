package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, String> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<Trainer> findByFirstNameAndLastName(String firstName, String lastName);
}