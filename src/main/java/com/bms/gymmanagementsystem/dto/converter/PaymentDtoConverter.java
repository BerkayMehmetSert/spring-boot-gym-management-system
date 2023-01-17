package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.PaymentDto;
import com.bms.gymmanagementsystem.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentDtoConverter {
    public PaymentDto convert(Payment from){
        return new PaymentDto(
                from.getId(),
                from.getDate(),
                from.getAmount(),
                from.getClient().getId()
        );
    }

    public Set<PaymentDto> convert(Set<Payment> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
