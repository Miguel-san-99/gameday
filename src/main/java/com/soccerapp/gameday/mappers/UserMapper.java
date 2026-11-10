package com.soccerapp.gameday.mappers;

import com.soccerapp.gameday.dtos.RegisterUserRequest;
import com.soccerapp.gameday.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        return user;
    }
}
