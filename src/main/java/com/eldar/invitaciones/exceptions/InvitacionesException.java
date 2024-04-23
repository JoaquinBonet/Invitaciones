package com.eldar.invitaciones.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvitacionesException extends RuntimeException {

  private InvitacionesErrorMessage invitacionesErrorMessage;

  public InvitacionesException(InvitacionesErrorMessage invitacionesErrorMessage) {

    super(invitacionesErrorMessage.getInternalErrorMessage());
    this.invitacionesErrorMessage = invitacionesErrorMessage;

  }

}
