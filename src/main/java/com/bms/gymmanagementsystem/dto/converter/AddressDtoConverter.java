package com.bms.gymmanagementsystem.dto.converter;

import com.bms.gymmanagementsystem.dto.AddressDto;
import com.bms.gymmanagementsystem.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address from){
        return new AddressDto(
                from.getId(),
                from.getStreet(),
                from.getCity(),
                from.getState(),
                from.getZipCode()
        );
    }
}
