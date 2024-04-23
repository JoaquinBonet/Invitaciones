package com.eldar.invitaciones.dtos;

import java.time.LocalDate;

public record PersonaResponse(Long id, String firstName, String lastName, String email, String dni, LocalDate birthDate, Boolean confirmed) {
}
