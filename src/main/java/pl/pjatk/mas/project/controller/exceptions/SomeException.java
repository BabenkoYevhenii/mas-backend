package pl.pjatk.mas.project.controller.exceptions;

public class SomeException extends RuntimeException {
    public SomeException(String message) {
        super("Tot samyj twoi exception: " + message);
    }
}
