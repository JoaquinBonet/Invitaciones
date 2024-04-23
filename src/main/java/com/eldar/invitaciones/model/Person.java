package com.eldar.invitaciones.model;

import com.eldar.invitaciones.dtos.PersonaResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String dni;
    private Boolean confirmed;


    public PersonaResponse response() {
        return new PersonaResponse(id, firstName, lastName, email, dni, birthDate, confirmed);
    }



}
