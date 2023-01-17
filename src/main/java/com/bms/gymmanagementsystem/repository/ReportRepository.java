package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {
}