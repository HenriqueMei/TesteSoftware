package br.unaerp.banco.exceptions;

public class IncorrectAccountException extends RuntimeException {
    public IncorrectAccountException() {
        super();
    }

    public IncorrectAccountException(String message) {
        super(message);
    }
}
