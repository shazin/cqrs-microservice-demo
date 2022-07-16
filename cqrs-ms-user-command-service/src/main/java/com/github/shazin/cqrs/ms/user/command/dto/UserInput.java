package com.github.shazin.cqrs.ms.user.command.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserInput(String id, String firstName, String lastName, LocalDate dateOfBirth, String identityNumber) implements Serializable {}
