package com.eldar.invitaciones.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InvitacionesErrorMessage {

  private HttpStatus httpStatus;
  private ErrorMessageEnum errorMessageEnum;
  private String internalErrorMessage;

  public InvitacionesErrorMessage(ErrorMessageEnum errorMessageEnum) {

    this.httpStatus = errorMessageEnum.getHttpStatus();
    this.errorMessageEnum = errorMessageEnum;
    this.internalErrorMessage = errorMessageEnum.getInternalErrorMessage();

  }


  public InvitacionesErrorMessageResponse response() {
    return new InvitacionesErrorMessageResponse(this);
  }

}
