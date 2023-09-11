package com.notbooking.overviewms.mapper;

import com.notbooking.overviewms.dto.AddressDTO;
import com.notbooking.overviewms.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    public Address toModel(AddressDTO dto) {
        return Address.builder()
                .street(dto.getStreet())
                .city(dto.getCity())
                .number(dto.getNumber())
                .build();
    }

    public AddressDTO toDto(Address model) {
        return AddressDTO.builder()
                .street(model.getStreet())
                .city(model.getCity())
                .number(model.getNumber())
                .build();
    }


    public List<AddressDTO> toDto(List<Address> models) {
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }
}
