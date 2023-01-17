package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.MembershipDto;
import com.bms.gymmanagementsystem.model.Membership;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MembershipDtoConverter {
    public MembershipDto convert(Membership from) {
        return new MembershipDto(
                from.getId(),
                from.getStatus(),
                from.getDate(),
                from.getClient().getId()
        );
    }

    public Set<MembershipDto> convert(Set<Membership> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
