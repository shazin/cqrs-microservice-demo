package com.github.shazin.cqrs.ms.user.command.repo;

import com.github.shazin.cqrs.ms.user.command.entity.UserCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommandRepository extends JpaRepository<UserCommand, String> {

}
