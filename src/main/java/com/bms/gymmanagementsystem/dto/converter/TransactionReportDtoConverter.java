package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.TransactionReportDto;
import com.bms.gymmanagementsystem.model.Report;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionReportDtoConverter {
    public TransactionReportDto convert(Report from) {
        return new TransactionReportDto(
                from.getId(),
                from.getDate(),
                from.getTotalAmount(),
                from.getClient().getId()
        );
    }

    public Set<TransactionReportDto> convert(Set<Report> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
