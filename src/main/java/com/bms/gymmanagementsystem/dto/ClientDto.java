package com.bms.gymmanagementsystem.dto;

import com.bms.gymmanagementsystem.model.Gender;

import java.util.Set;

public record ClientDto(
        String id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender,
        AddressDto address,
        String email,
        String phoneNumber,
        String password,
        Set<ClientMembershipDto> memberships,
        Set<ClientPaymentDto> payments,
        Set<ClientScheduleDto> schedules,
        Set<ClientTransactionDto> transactions,
        Set<ClientReportDto> reports
) {
}
