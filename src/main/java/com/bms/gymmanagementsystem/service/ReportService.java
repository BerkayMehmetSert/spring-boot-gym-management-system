package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.ReportDto;
import com.bms.gymmanagementsystem.dto.converter.ReportDtoConverter;
import com.bms.gymmanagementsystem.exception.ReportNotFoundException;
import com.bms.gymmanagementsystem.helper.DateHelper;
import com.bms.gymmanagementsystem.model.Report;
import com.bms.gymmanagementsystem.model.Transaction;
import com.bms.gymmanagementsystem.repository.ReportRepository;
import com.bms.gymmanagementsystem.request.report.CreateReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ClientService clientService;
    private final TransactionService transactionService;
    private final ReportDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportService(ReportRepository reportRepository,
                         ClientService clientService,
                         TransactionService transactionService,
                         ReportDtoConverter converter) {
        this.reportRepository = reportRepository;
        this.clientService = clientService;
        this.transactionService = transactionService;
        this.converter = converter;
    }

    public void createReport(final CreateReportRequest request) {
        Transaction transaction = transactionService.getTransactionById(request.getTransactionId());
        Report report = new Report(
                DateHelper.getCurrentDate(),
                transaction.getAmount(),
                clientService.getClientById(request.getClientId()),
                transaction
        );

        reportRepository.save(report);
        logger.info("Report created successfully");
    }

    public void deleteReport(final String id) {
        reportRepository.delete(getReportById(id));
        logger.info("Report deleted successfully with id: {}", id);
    }

    public Set<ReportDto> findAllReports() {
        Set<Report> reports = new HashSet<>(reportRepository.findAll());

        if (reports.isEmpty()) {
            logger.error("Report list is empty");
            throw new ReportNotFoundException("Report list is empty");
        }

        logger.info("Report list found successfully with size: " + reports.size());
        return converter.convert(reports);
    }

    private Report getReportById(final String id) {
        return reportRepository.findById(id).orElseThrow(() -> {
            logger.error("Report with id {} not found", id);
            throw new ReportNotFoundException("Report with id " + id + " not found");
        });
    }
}
