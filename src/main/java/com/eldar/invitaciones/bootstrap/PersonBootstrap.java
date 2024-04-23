package com.eldar.invitaciones.bootstrap;

import com.eldar.invitaciones.model.Person;
import com.eldar.invitaciones.repositories.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonBootstrap {

    private final PersonaRepository personaRepository;


    @Value("${bootstrap}")
    private String boostrap;


    void run() {
        if (Boolean.valueOf(boostrap).equals(true)) {

            if (personaRepository.count() == 0) {

                Person example = Person.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("johndoe@gmail.com")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .dni("12345678")
                        .confirmed(false)
                        .build();


                Person example2 = Person.builder()
                        .firstName("Pablo")
                        .lastName("Gonzalez")
                        .email("pgonzalez@gmail.com")
                        .birthDate(LocalDate.of(1990, 11, 13))
                        .dni("3231232")
                        .confirmed(false)
                        .build();


                Person example3 = Person.builder()
                        .firstName("Maria")
                        .lastName("Garcia")
                        .email("mgarcia@gmail.com")
                        .birthDate(LocalDate.of(1990, 2, 3))
                        .dni("3231232")
                        .confirmed(true)
                        .build();

                personaRepository.saveAll(List.of(example, example2, example3));

            }
        }
    }
}
