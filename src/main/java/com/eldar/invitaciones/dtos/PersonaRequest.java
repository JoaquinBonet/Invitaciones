package com.eldar.invitaciones.dtos;

import java.time.LocalDate;

public record PersonaRequest(String firstName, String lastName, String email, String dni, LocalDate birthDate) {
}
