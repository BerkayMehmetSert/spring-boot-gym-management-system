package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.ScheduleDto;
import com.bms.gymmanagementsystem.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ScheduleDtoConverter {
    public ScheduleDto convert(Schedule from) {
        return new ScheduleDto(
                from.getId(),
                from.getSession(),
                from.getActivity(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getClient().getId(),
                from.getTrainer().getId()
        );
    }

    public Set<ScheduleDto> convert(Set<Schedule> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
