package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.ScheduleDto;
import com.bms.gymmanagementsystem.request.schedule.CreateScheduleRequest;
import com.bms.gymmanagementsystem.request.schedule.UpdateScheduleRequest;
import com.bms.gymmanagementsystem.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/v1/schedule")
public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody CreateScheduleRequest request) {
        service.createSchedule(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable String id,
                                               @RequestBody UpdateScheduleRequest request) {
        service.updateSchedule(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/start-time")
    public ResponseEntity<Void> updateScheduleStartTime(@PathVariable String id,
                                                        @RequestBody LocalDateTime startTime) {
        service.updateScheduleStartTime(id, startTime);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/end-time")
    public ResponseEntity<Void> updateScheduleEndTime(@PathVariable String id,
                                                      @RequestBody LocalDateTime endTime) {
        service.updateScheduleEndTime(id, endTime);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/trainer")
    public ResponseEntity<Void> updateScheduleTrainer(@PathVariable String id,
                                                      @RequestBody String trainerId) {
        service.updateScheduleTrainer(id, trainerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable String id) {
        service.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<ScheduleDto>> findAllSchedules() {
        return ResponseEntity.ok(service.findAllSchedules());
    }
}
