package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ClientScheduleDto;
import com.bms.gymmanagementsystem.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientScheduleDtoConverter {
    public ClientScheduleDto convert(Schedule from) {
        return new ClientScheduleDto(
                from.getId(),
                from.getSession(),
                from.getActivity(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getTrainer().getId()
        );
    }

    public Set<ClientScheduleDto> convert(Set<Schedule> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
