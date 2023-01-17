package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientPaymentDto;
import com.bms.gymmanagementsystem.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientPaymentDtoConverter {
    public ClientPaymentDto convert(Payment from) {
        return new ClientPaymentDto(
                from.getId(),
                from.getDate(),
                from.getAmount()
        );
    }

    public Set<ClientPaymentDto> convert(Set<Payment> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
