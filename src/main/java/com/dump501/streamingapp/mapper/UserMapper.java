package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserRequest;
import com.dump501.streamingapp.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toEntity(UserRequest userRequest);
    List<UserDto> toDto(List<User> users);
    List<User> toEntity(List<UserDto> userDtos);

    default User fromId(UUID uuid){
        if(uuid == null){
            return null;
        }
        User user = new User();
        user.setUuid(uuid);
        return user;
    }
}
