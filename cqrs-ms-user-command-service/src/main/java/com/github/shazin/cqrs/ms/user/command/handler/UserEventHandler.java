package com.github.shazin.cqrs.ms.user.command.handler;

import com.github.shazin.cqrs.ms.user.dto.UserEvent;

public interface UserEventHandler {
    public boolean publishUserEvent(UserEvent event);
}
