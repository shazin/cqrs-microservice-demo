package com.github.shazin.cqrs.ms.user.query.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.user.dto.User;
import com.github.shazin.cqrs.ms.user.dto.UserCreateEvent;
import com.github.shazin.cqrs.ms.user.dto.UserDeleteEvent;
import com.github.shazin.cqrs.ms.user.query.entity.UserEntity;
import com.github.shazin.cqrs.ms.user.query.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class UserEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserEventListener.class);

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public UserEventListener(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "${kafka.topic.name}")
    public void consume(String message) {
        try {
            Map<String, String> userEvent = objectMapper.readValue(message, Map.class);
            if (userEvent.get("type").equals("CREATE")) {
                UserCreateEvent userCreateEvent = objectMapper.readValue(message, UserCreateEvent.class);
                User user = userCreateEvent.user();
                userRepository.save(new UserEntity(UUID.randomUUID().toString(), user.firstName(), user.lastName(), user.dateOfBirth(), user.identityNumber()));
            } else if (userEvent.get("type").equals("DELETE")) {
                UserDeleteEvent userDeleteEvent = objectMapper.readValue(message, UserDeleteEvent.class);
                userRepository.deleteById(userDeleteEvent.id());
            }
        } catch (Exception e) {
            LOGGER.error("Error while handling message", e);
        }
    }
}
