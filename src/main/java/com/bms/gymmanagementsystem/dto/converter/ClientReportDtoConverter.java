package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientReportDto;
import com.bms.gymmanagementsystem.model.Report;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientReportDtoConverter {
    public ClientReportDto convert(Report from) {
        return new ClientReportDto(
                from.getId(),
                from.getDate(),
                from.getTotalAmount(),
                from.getTransaction().getId()
        );
    }

    public Set<ClientReportDto> convert(Set<Report> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
