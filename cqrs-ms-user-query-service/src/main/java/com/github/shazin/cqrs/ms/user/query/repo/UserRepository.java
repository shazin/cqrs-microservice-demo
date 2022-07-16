package com.github.shazin.cqrs.ms.user.query.repo;

import com.github.shazin.cqrs.ms.user.query.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
