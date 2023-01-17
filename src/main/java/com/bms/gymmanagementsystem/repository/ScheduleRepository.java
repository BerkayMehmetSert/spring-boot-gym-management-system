package com.bms.gymmanagementsystem.repository;

import com.bms.gymmanagementsystem.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}