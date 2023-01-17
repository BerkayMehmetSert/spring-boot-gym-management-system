package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientMembershipDto;
import com.bms.gymmanagementsystem.model.Membership;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientMembershipDtoConverter {
    public ClientMembershipDto convert(Membership from){
        return new ClientMembershipDto(
                from.getId(),
                from.getStatus(),
                from.getDate()
        );
    }

    public Set<ClientMembershipDto> convert(Set<Membership> from){
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
