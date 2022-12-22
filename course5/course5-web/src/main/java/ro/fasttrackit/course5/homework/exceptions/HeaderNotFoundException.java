package ro.fasttrackit.course5.homework.exceptions;

public class HeaderNotFoundException extends RuntimeException {
    public HeaderNotFoundException(String message) {
        super(message);
    }
}
