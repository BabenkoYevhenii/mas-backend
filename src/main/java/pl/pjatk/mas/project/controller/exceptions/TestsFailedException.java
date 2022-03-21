package pl.pjatk.mas.project.controller.exceptions;

public class TestsFailedException extends RuntimeException {
    public TestsFailedException() {
        super("Tests failed");
    }
}