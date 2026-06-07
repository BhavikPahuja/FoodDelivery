package com.jpa.fooddelivery.Mappers;

import com.jpa.fooddelivery.Entities.Authorities;
import com.jpa.fooddelivery.Payloads.AuthoritiesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    AuthoritiesDto toAuthoritiesDto(Authorities authorities);
    Authorities toAuthorities(AuthoritiesDto authoritiesDto);
}
