package com.github.shazin.cqrs.ms.user.query.controller;

import com.github.shazin.cqrs.ms.user.dto.User;
import com.github.shazin.cqrs.ms.user.query.repo.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserQueryController {

    private final UserRepository userRepository;

    public UserQueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SchemaMapping(typeName = "Query", field = "allUsers")
    public List<User> allUsers() {
        return userRepository.findAll().stream().map(userEntity -> new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getDateOfBirth(), userEntity.getIdentityNumber())).toList();
    }

    @SchemaMapping(typeName = "Query", field = "findOne")
    public User findOne(@Argument String id) {
        return userRepository.findById(id).map(userEntity -> new User(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getDateOfBirth(), userEntity.getIdentityNumber())).orElse(null);
    }
}
