package com.github.shazin.cqrs.ms.user.command.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.user.command.entity.UserCommand;
import com.github.shazin.cqrs.ms.user.command.repo.UserCommandRepository;
import com.github.shazin.cqrs.ms.user.command.handler.UserEventHandler;
import com.github.shazin.cqrs.ms.user.dto.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultUserEventHandler implements UserEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserEventHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopicName;
    private final ObjectMapper objectMapper;
    private final UserCommandRepository userCommandRepository;


    public DefaultUserEventHandler(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic.name}") String kafkaTopicName, ObjectMapper objectMapper, UserCommandRepository userCommandRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicName = kafkaTopicName;
        this.objectMapper = objectMapper;
        this.userCommandRepository = userCommandRepository;
    }

    public boolean publishUserEvent(UserEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            this.kafkaTemplate.send(kafkaTopicName, payload);
            this.userCommandRepository.save(new UserCommand(UUID.randomUUID().toString(), payload, event.createdDate(), event.createdBy(), event.getType()));
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while publishing user", e);
            return false;
        }
    }


}
