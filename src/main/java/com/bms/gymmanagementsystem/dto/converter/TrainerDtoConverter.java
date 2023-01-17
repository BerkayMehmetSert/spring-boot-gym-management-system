package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.TrainerDto;
import com.bms.gymmanagementsystem.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TrainerDtoConverter {
    private final TrainerScheduleDtoConverter scheduleDtoConverter;
    private final AddressDtoConverter addressDtoConverter;

    public TrainerDtoConverter(TrainerScheduleDtoConverter scheduleDtoConverter,
                               AddressDtoConverter addressDtoConverter) {
        this.scheduleDtoConverter = scheduleDtoConverter;
        this.addressDtoConverter = addressDtoConverter;
    }

    public TrainerDto convert(Trainer from) {
        return new TrainerDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getGender(),
                from.getSalary(),
                from.getAddress() != null ? addressDtoConverter.convert(from.getAddress()) : null,
                from.getSchedules() != null ? scheduleDtoConverter.convert(from.getSchedules()) : null
        );
    }

    public Set<TrainerDto> convert(Set<Trainer> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
