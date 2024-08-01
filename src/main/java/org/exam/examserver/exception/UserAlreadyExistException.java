package org.exam.examserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class UserAlreadyExistException
  extends ErrorResponseException
{
  public UserAlreadyExistException()
  {
    super(HttpStatus.CONFLICT, ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "User already exists in db"), null);
  }
}
