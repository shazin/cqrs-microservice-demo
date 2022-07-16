package com.github.shazin.cqrs.ms.user.dto;

public record UserDeleteEvent(String id) implements UserEvent {

    @Override
    public String getType() {
        return "DELETE";
    }
}
