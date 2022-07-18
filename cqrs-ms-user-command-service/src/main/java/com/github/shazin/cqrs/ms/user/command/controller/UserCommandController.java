package com.github.shazin.cqrs.ms.user.command.controller;

import com.github.shazin.cqrs.ms.user.command.dto.UserInput;
import com.github.shazin.cqrs.ms.user.command.entity.UserEntity;
import com.github.shazin.cqrs.ms.user.command.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserCommandController {

    private final UserService userService;

    public UserCommandController(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public UserEntity createUser(@Argument UserInput user) {
        UserEntity userCreated = new UserEntity(UUID.randomUUID().toString(), user.firstName(), user.lastName(), user.dateOfBirth(), user.identityNumber());
        userService.createUser(userCreated);
        return userCreated;
    }

    @MutationMapping
    public String deleteUser(@Argument String id) {
        userService.deleteUserById(id);
        return id;
    }

}
