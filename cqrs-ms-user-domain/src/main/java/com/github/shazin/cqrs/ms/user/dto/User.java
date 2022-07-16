package com.github.shazin.cqrs.ms.user.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record User(String id, String firstName, String lastName, LocalDate dateOfBirth, String identityNumber) implements Serializable {}
