package com.eldar.invitaciones.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;

@Slf4j
@ControllerAdvice
public class InvitacionesExceptionHandler {

  @ExceptionHandler({InvitacionesException.class})
  public final ResponseEntity<InvitacionesErrorMessageResponse> handleInvitacionesException(
          InvitacionesException oceanException) {

    log.error("Invitaciones - " + oceanException.getMessage());

    return new ResponseEntity<>(
            oceanException.getInvitacionesErrorMessage().response(),
        HttpStatus.CONFLICT);

  }



}
