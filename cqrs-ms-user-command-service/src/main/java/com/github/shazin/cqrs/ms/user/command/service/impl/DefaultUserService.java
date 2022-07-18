package com.github.shazin.cqrs.ms.user.command.service.impl;

import com.github.shazin.cqrs.ms.user.command.entity.UserEntity;
import com.github.shazin.cqrs.ms.user.command.repo.UserRepository;
import com.github.shazin.cqrs.ms.user.command.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserById(String id) {
        userRepository.deleteById(id);
        return true;
    }
}
