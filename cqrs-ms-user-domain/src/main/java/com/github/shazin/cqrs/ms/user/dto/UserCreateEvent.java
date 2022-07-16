package com.github.shazin.cqrs.ms.user.dto;

public record UserCreateEvent(User user) implements UserEvent {

    @Override
    public String getType() {
        return "CREATE";
    }
}
