package br.unaerp.banco.exceptions;

public class WithoutBalanceException extends RuntimeException {
    public WithoutBalanceException() {
        super();
    }

    public WithoutBalanceException(String message) {
        super(message);
    }

}
