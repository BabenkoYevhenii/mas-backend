package pl.pjatk.mas.project.controller.exceptions;

import lombok.Getter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Getter
public class ValidatorExecutionException extends RuntimeException {
  private String className;

  private Type type;

  public ValidatorExecutionException(Exception e) {
    super(e.getMessage());
    if (e == null) {
      className = "unknown";
    } else {
      className = e.getClass().getName();
      if (e instanceof TimeoutException) {
        type = Type.Timeout;
      } else if (e instanceof ExecutionException) {
        type = Type.Execution;
      } else if (e instanceof InterruptedException) {
        type = Type.Interrupt;
      } else {
        type = Type.UnknownException;
      }
    }
  }

  public ValidatorExecutionException(Type type) {
    super(type == null ? " Unknown error" : type.toString());
  }

  public enum Type {
    Timeout,
    Interrupt,
    Execution,
    ValidatorTypeUnknown,
    UnknownException
  }
}
