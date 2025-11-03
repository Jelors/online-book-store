package com.springm.store.mapper;

import com.springm.store.config.MapperConfig;
import com.springm.store.dto.user.CreateUserRequestDto;
import com.springm.store.dto.user.UserDto;
import com.springm.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    User toModel(CreateUserRequestDto createUserRequestDto);

    void updateUserFromDto(CreateUserRequestDto changedUser, @MappingTarget User user);
}
