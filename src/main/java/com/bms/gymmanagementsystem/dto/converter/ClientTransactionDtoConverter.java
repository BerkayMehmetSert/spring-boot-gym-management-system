package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientTransactionDto;
import com.bms.gymmanagementsystem.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientTransactionDtoConverter {
    public ClientTransactionDto convert(Transaction from) {
        return new ClientTransactionDto(
                from.getId(),
                from.getName(),
                from.getAmount(),
                from.getDate()
        );
    }

    public Set<ClientTransactionDto> convert(Set<Transaction> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
