package com.eldar.invitaciones.exceptions;

import lombok.Data;

@Data
public class InvitacionesErrorMessageResponse {

  private Integer httpStatusCode;
  private String httpStatusDescription;
  private String displayMessage;
  private String errorType;

  public InvitacionesErrorMessageResponse(InvitacionesErrorMessage invitacionesErrorMessage) {

    this.errorType = invitacionesErrorMessage.getErrorMessageEnum().toString();
    this.httpStatusCode = invitacionesErrorMessage.getHttpStatus().value();
    this.httpStatusDescription = invitacionesErrorMessage.getHttpStatus().getReasonPhrase();
    this.displayMessage = invitacionesErrorMessage.getInternalErrorMessage();

  }

}
