package com.jpa.fooddelivery.Mappers;

import com.jpa.fooddelivery.Entities.User;
import com.jpa.fooddelivery.Payloads.Requests.UserRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.UserResponseDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {AddressMapper.class, AuthorityMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserResponseDto toUserResponseDto(User user);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toUser(UserRequestDto userRequestDto);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User existingUser);
}