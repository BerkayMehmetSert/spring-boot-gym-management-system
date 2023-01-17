package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.ReportDto;
import com.bms.gymmanagementsystem.request.report.CreateReportRequest;
import com.bms.gymmanagementsystem.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/report")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createReport(@RequestBody CreateReportRequest request) {
        service.createReport(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        service.deleteReport(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<ReportDto>> findAllReports() {
        return ResponseEntity.ok(service.findAllReports());
    }
}
