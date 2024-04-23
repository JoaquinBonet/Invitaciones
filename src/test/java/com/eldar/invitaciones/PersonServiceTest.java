package com.eldar.invitaciones;

import static com.eldar.invitaciones.TestUtils.getValidPerson;
import static com.eldar.invitaciones.TestUtils.getValidPersonRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eldar.invitaciones.exceptions.InvitacionesException;
import com.eldar.invitaciones.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eldar.invitaciones.dtos.PersonaRequest;
import com.eldar.invitaciones.model.Person;
import com.eldar.invitaciones.repositories.PersonaRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonService personService;

    private ArgumentCaptor<Person> personArgumentCaptor;


    @Test
    public void testPostPerson() {

        // GIVEN
        var personRequest = getValidPersonRequest();
        this.personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        // WHEN
        personService.postPerson(personRequest);

        // THEN
        verify(personaRepository).save(personArgumentCaptor.capture());

        assertThat(personArgumentCaptor.getValue().getFirstName()).isEqualTo(personRequest.firstName());
        assertThat(personArgumentCaptor.getValue().getLastName()).isEqualTo(personRequest.lastName());
        assertThat(personArgumentCaptor.getValue().getEmail()).isEqualTo(personRequest.email());
        assertThat(personArgumentCaptor.getValue().getDni()).isEqualTo(personRequest.dni());
        assertThat(personArgumentCaptor.getValue().getBirthDate()).isEqualTo(personRequest.birthDate());
        assertThat(personArgumentCaptor.getValue().getConfirmed()).isFalse();
    }

    @Test
    public void testGetPersonByIdThrowsException() {

        assertThatThrownBy(() -> personService.getPersonById(923923L))
                .isInstanceOf(InvitacionesException.class)
                .hasMessageContaining("Persona no encontrada");
    }

    @Test
    public void testModifyPerson() {
        // GIVEN
        var testPerson = getValidPerson();
        var personRequest = getValidPersonRequest();
        this.personArgumentCaptor = ArgumentCaptor.forClass(Person.class);

        when(personaRepository.findById(1L)).thenReturn(Optional.of(testPerson));

        //WHEN
        personService.modifyPerson(1L, personRequest);

        //THEN
        verify(personaRepository).save(personArgumentCaptor.capture());

        assertThat(personArgumentCaptor.getValue().getFirstName()).isEqualTo(personRequest.firstName());
        assertThat(personArgumentCaptor.getValue().getLastName()).isEqualTo(personRequest.lastName());
        assertThat(personArgumentCaptor.getValue().getEmail()).isEqualTo(personRequest.email());
        assertThat(personArgumentCaptor.getValue().getDni()).isEqualTo(personRequest.dni());
        assertThat(personArgumentCaptor.getValue().getBirthDate()).isEqualTo(personRequest.birthDate());
    }

    @Test
    public void testPersonConfirmedValidation() {
        //GIVEN
        var testPerson = getValidPerson();
        testPerson.setConfirmed(true);

        //WHEN
        //THEN
        assertThatThrownBy(() -> personService.validatePersonNotConfirmed(testPerson))
                .isInstanceOf(InvitacionesException.class)
                .hasMessageContaining("La persona ya esta confirmada, no se puede modificar/eliminar");
    }

    @Test
    public void testDeletePerson() {

        //GIVEN
        var testPerson = getValidPerson();
        when(personaRepository.findById(1L)).thenReturn(Optional.of(testPerson));

        //WHEN
        personService.deletePerson(1L);

        //THEN
        verify(personaRepository).deleteById(1L);
    }
}

