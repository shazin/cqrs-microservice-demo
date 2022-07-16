package com.github.shazin.cqrs.ms.user.command.controller;

import com.github.shazin.cqrs.ms.user.command.dto.UserInput;
import com.github.shazin.cqrs.ms.user.command.handler.UserEventHandler;
import com.github.shazin.cqrs.ms.user.dto.User;
import com.github.shazin.cqrs.ms.user.dto.UserCreateEvent;
import com.github.shazin.cqrs.ms.user.dto.UserDeleteEvent;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserCommandController {

    private final UserEventHandler userEventHandler;

    public UserCommandController(UserEventHandler userService) {
        this.userEventHandler = userService;
    }

    @MutationMapping
    public User createUser(@Argument UserInput user) {
        User userCreated = new User(UUID.randomUUID().toString(), user.firstName(), user.lastName(), user.dateOfBirth(), user.identityNumber());
        UserCreateEvent userCreateEvent = new UserCreateEvent(userCreated);
        userEventHandler.publishUserEvent(userCreateEvent);
        return userCreated;
    }

    @MutationMapping
    public String deleteUser(@Argument String id) {
        UserDeleteEvent userDeleteEvent = new UserDeleteEvent(id);
        userEventHandler.publishUserEvent(userDeleteEvent);
        return id;
    }

}
