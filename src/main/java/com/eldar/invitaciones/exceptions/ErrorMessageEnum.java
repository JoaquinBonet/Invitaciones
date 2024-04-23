package com.eldar.invitaciones.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorMessageEnum {

    PERSON_NOT_FOUND("Persona no encontrada", HttpStatus.NOT_FOUND),
    EMAIL_NOT_NULL("El email no puede ser nulo", HttpStatus.CONFLICT),
    DNI_NOT_NULL("DNI no puede ser nulo", HttpStatus.CONFLICT),
    EMAIL_REPEATED("El email esta repetido", HttpStatus.CONFLICT),
    DNI_REPEATED("El DNI esta repetido", HttpStatus.CONFLICT),
    PERSON_ALREADY_CONFIRMED("La persona ya esta confirmada, no se puede modificar/eliminar", HttpStatus.CONFLICT),
    ;



    private final String internalErrorMessage;
    private final HttpStatus httpStatus;

    ErrorMessageEnum(String internalErrorMessage, HttpStatus httpStatus) {

        this.internalErrorMessage = internalErrorMessage;
        this.httpStatus = httpStatus;
    }

    public String getInternalErrorMessage() {
        return this.internalErrorMessage;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
