package org.example.exception;

public class CredFailedException extends RuntimeException{
    public CredFailedException() {
        super("Пароль или логин не верный!");
    }
}
