package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientDto;
import com.bms.gymmanagementsystem.model.Client;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientDtoConverter {
    private final ClientMembershipDtoConverter membershipDtoConverter;
    private final ClientPaymentDtoConverter paymentDtoConverter;
    private final ClientScheduleDtoConverter scheduleDtoConverter;
    private final ClientTransactionDtoConverter transactionDtoConverter;
    private final ClientReportDtoConverter reportDtoConverter;
    private final AddressDtoConverter addressDtoConverter;

    public ClientDtoConverter(ClientMembershipDtoConverter membershipDtoConverter,
                              ClientPaymentDtoConverter paymentDtoConverter,
                              ClientScheduleDtoConverter scheduleDtoConverter,
                              ClientTransactionDtoConverter transactionDtoConverter,
                              ClientReportDtoConverter reportDtoConverter,
                              AddressDtoConverter addressDtoConverter) {
        this.membershipDtoConverter = membershipDtoConverter;
        this.paymentDtoConverter = paymentDtoConverter;
        this.scheduleDtoConverter = scheduleDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
        this.reportDtoConverter = reportDtoConverter;
        this.addressDtoConverter = addressDtoConverter;
    }

    public ClientDto convert(Client from) {
        from.getAddress();
        return new ClientDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getAge(),
                from.getGender(),
                addressDtoConverter.convert(from.getAddress()),
                from.getEmail(),
                from.getPhoneNumber(),
                from.getPassword(),
                from.getMemberships() != null ? membershipDtoConverter.convert(from.getMemberships()) : null,
                from.getPayments() != null ? paymentDtoConverter.convert(from.getPayments()) : null,
                from.getSchedules() != null ? scheduleDtoConverter.convert(from.getSchedules()) : null,
                from.getTransactions() != null ? transactionDtoConverter.convert(from.getTransactions()) : null,
                from.getReports() != null ? reportDtoConverter.convert(from.getReports()) : null
        );
    }

    public Set<ClientDto> convert(Set<Client> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}

