package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ReportDto;
import com.bms.gymmanagementsystem.model.Report;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReportDtoConverter {
    public ReportDto convert(Report from) {
        return new ReportDto(
                from.getId(),
                from.getDate(),
                from.getTotalAmount(),
                from.getClient().getId(),
                from.getTransaction().getId()
        );
    }

    public Set<ReportDto> convert(Set<Report> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
