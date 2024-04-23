package com.eldar.invitaciones.service;


import com.eldar.invitaciones.dtos.PersonaRequest;
import com.eldar.invitaciones.exceptions.ErrorMessageEnum;
import com.eldar.invitaciones.exceptions.InvitacionesErrorMessage;
import com.eldar.invitaciones.exceptions.InvitacionesException;
import com.eldar.invitaciones.model.Person;
import com.eldar.invitaciones.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PersonService {


    private final PersonaRepository personaRepository;

    public Page<Person> getPersons(Specification<Person> specification, Pageable pageable) {
        return personaRepository.findAll(specification, pageable);
    }


    public Person postPerson(PersonaRequest personaRequest) {

        validateNotNullAndUniqueEmailAndDni(personaRequest);

        Person person = Person.builder()
                .firstName(personaRequest.firstName())
                .lastName(personaRequest.lastName())
                .email(personaRequest.email())
                .birthDate(personaRequest.birthDate())
                .dni(personaRequest.dni())
                .confirmed(false)
                .build();

        return personaRepository.save(person);
    }

    private void validateNotNullAndUniqueEmailAndDni(PersonaRequest personaRequest) {

        if (isNull(personaRequest.email())) {
            throw new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.EMAIL_NOT_NULL));
        }

        if (isNull(personaRequest.dni())) {
            throw new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.DNI_NOT_NULL));
        }

        if (personaRepository.existsByEmail(personaRequest.email())) {
            throw new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.EMAIL_REPEATED));
        }

        if (personaRepository.existsByDni(personaRequest.dni())) {
            throw new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.DNI_REPEATED));
        }



    }


    public Person getPersonById(long personId) {
        return personaRepository.findById(personId).
                orElseThrow(() -> new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.PERSON_NOT_FOUND)));

    }


    public Person modifyPerson(long personId, PersonaRequest personaRequest) {

        Person person = getPersonById(personId);

        validatePersonNotConfirmed(person);

        Person updatedPerson = Person.builder()
                .id(personId)
                .email(person.getEmail())
                .dni(person.getDni())
                .firstName(personaRequest.firstName())
                .lastName(personaRequest.lastName())
                .birthDate(personaRequest.birthDate())
                .confirmed(person.getConfirmed())
                .build();

        return personaRepository.save(updatedPerson);

    }


    public void deletePerson(long personId) {
        Person person = getPersonById(personId);
        validatePersonNotConfirmed(person);

        personaRepository.deleteById(personId);
    }


    public Person confirmPerson(long personId) {
        Person person = getPersonById(personId);
        person.setConfirmed(true);
        return personaRepository.save(person);
    }



    public void validatePersonNotConfirmed(Person person) {
        if(person.getConfirmed().equals(true)) {
            throw new InvitacionesException(new InvitacionesErrorMessage(ErrorMessageEnum.PERSON_ALREADY_CONFIRMED));
        }
    }

}
