package com.github.shazin.cqrs.ms.user.query.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.user.query.entity.UserEntity;
import com.github.shazin.cqrs.ms.user.query.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

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
    public void consume(@Payload(required = false) String message) {
        if (StringUtils.hasText(message)) {
            try {
                Map<String, Object> event = objectMapper.readValue(message, Map.class);
                Map<String, Object> payload = (Map<String, Object>) event.get("payload");
                if (payload.get("before") == null && payload.get("after") != null) {
                    LOGGER.info("Create/Update Event");
                    UserEntity userToBeCreated = objectMapper.convertValue(payload.get("after"), UserEntity.class);
                    userRepository.save(userToBeCreated);
                } else if (payload.get("before") != null && payload.get("after") == null) {
                    LOGGER.info("Delete Event");
                    UserEntity userToBeDeleted = objectMapper.convertValue(payload.get("before"), UserEntity.class);
                    userRepository.delete(userToBeDeleted);
                }
            } catch (Exception e) {
                LOGGER.error("Error while handling message", e);
            }
        }
    }
}
