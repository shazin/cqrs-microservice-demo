package com.github.shazin.cqrs.ms.user.dto;

import java.util.Date;

public interface UserEvent {

    default String getType() {
        return "CREATE";
    }

    default Date createdDate() {
        return new Date();
    }

    default String createdBy() {
        return "cqrs-ms-command";
    }
}
