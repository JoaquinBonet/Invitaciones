package com.eldar.invitaciones.repositories;

import com.eldar.invitaciones.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonaRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
