package com.eldar.invitaciones;

import com.eldar.invitaciones.dtos.PersonaRequest;
import com.eldar.invitaciones.model.Person;

import java.time.LocalDate;

public class TestUtils {

    public static Person getValidPerson(){

        return Person.builder()
                .id(23L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juanperez@gmail.com")
                .dni("12345678")
                .birthDate(LocalDate.of(1990, 1, 1))
                .confirmed(false)
                .build();
    }


    public static PersonaRequest getValidPersonRequest(){

        return new PersonaRequest("test", "test", "test@gmail.com", "1234567", LocalDate.of(1990, 1, 1));
    }
}
