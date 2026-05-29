package com.jpa.fooddelivery.Mappers;

import com.jpa.fooddelivery.Entities.Address;
import com.jpa.fooddelivery.Payloads.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toAddressDto(Address address);
    Address toAddress(AddressDto addressDto);
}