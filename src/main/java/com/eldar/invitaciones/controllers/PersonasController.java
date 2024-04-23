package com.eldar.invitaciones.controllers;


import com.eldar.invitaciones.commons.FilterManager;
import com.eldar.invitaciones.dtos.PagedResponse;
import com.eldar.invitaciones.dtos.PersonaRequest;
import com.eldar.invitaciones.dtos.PersonaResponse;
import com.eldar.invitaciones.model.Person;
import com.eldar.invitaciones.model.SortEnum;
import com.eldar.invitaciones.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.eldar.invitaciones.commons.SpecificationFilters.personConfirmedFilter;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonasController {

    private final PersonService personService;
    private final FilterManager filterManager;


    @GetMapping()
    public ResponseEntity<PagedResponse> getPersons(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "DESC") SortEnum sort,
            @RequestParam(required = false) Boolean confirmed) {

        Specification<Person> filter = Specification.where(personConfirmedFilter(confirmed));

        Page<Person> persons = personService.getPersons(
                filter,
                filterManager.getPageable(sort, pageNumber, pageSize)
                );

        return new ResponseEntity<>(
                new PagedResponse(persons.getNumber(),
                        persons.getNumberOfElements(),
                        persons.getTotalPages(),
                        persons.getTotalElements(),
                        persons.stream().map(Person::response).collect(Collectors.toList())),
                HttpStatus.OK);

    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonaResponse> getPersonById(
            @PathVariable("personId") Long personId) {

        return new ResponseEntity<>(
                personService.getPersonById(personId).response(),
                HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<PersonaResponse> createPerson(
            @RequestBody PersonaRequest personaRequest) {

        return new ResponseEntity<>(
                personService.postPerson(personaRequest).response(), HttpStatus.OK);

    }

    @PutMapping("/{personaId}")
    public ResponseEntity<PersonaResponse> modifyPerson(
            @PathVariable Long personaId,
            @RequestBody PersonaRequest personaRequest) {

        return new ResponseEntity<>(
                personService.modifyPerson(personaId, personaRequest).response(),
                HttpStatus.OK);
    }


    @PostMapping("/{personaId}/confirm")
    public ResponseEntity<PersonaResponse> confirmPerson(
            @PathVariable Long personaId) {

        return new ResponseEntity<>(
                personService.confirmPerson(personaId).response(),
                HttpStatus.OK);
    }


    @DeleteMapping("/{personaId}")
    public ResponseEntity<PersonaResponse> deletePerson(
            @PathVariable Long personaId) {

        personService.deletePerson(personaId);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }


}
