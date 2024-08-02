package org.exam.examserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class UserAlreadyExistException
  extends ErrorResponseException
{
  public UserAlreadyExistException()
  {
    super(HttpStatus.CONFLICT, problemDetailFrom("User with this username already exists in database. Please try registration with a different username"), null);
  }

  private static ProblemDetail problemDetailFrom(String message)
  {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
    problemDetail.setTitle("Username is already in use");
    return problemDetail;
  }
}
