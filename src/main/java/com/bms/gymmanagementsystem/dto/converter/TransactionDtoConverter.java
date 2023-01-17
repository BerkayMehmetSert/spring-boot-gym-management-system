package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.TransactionDto;
import com.bms.gymmanagementsystem.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionDtoConverter {
    private final TransactionReportDtoConverter reportDtoConverter;

    public TransactionDtoConverter(TransactionReportDtoConverter reportDtoConverter) {
        this.reportDtoConverter = reportDtoConverter;
    }

    public TransactionDto convert(Transaction from) {
        return new TransactionDto(
                from.getId(),
                from.getName(),
                from.getAmount(),
                from.getDate(),
                from.getClient().getId(),
                from.getReports() != null ? reportDtoConverter.convert(from.getReports()) : null
        );
    }

    public Set<TransactionDto> convert(Set<Transaction> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
