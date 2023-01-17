package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.TrainerScheduleDto;
import com.bms.gymmanagementsystem.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TrainerScheduleDtoConverter {
    public TrainerScheduleDto convert(Schedule from) {
        return new TrainerScheduleDto(
                from.getId(),
                from.getSession(),
                from.getActivity(),
                from.getDate(),
                from.getStartTime(),
                from.getEndTime(),
                from.getClient().getId()
        );
    }

    public Set<TrainerScheduleDto> convert(Set<Schedule> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
