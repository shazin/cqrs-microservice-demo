package com.github.shazin.cqrs.ms.user.command.repo;

import com.github.shazin.cqrs.ms.user.command.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
