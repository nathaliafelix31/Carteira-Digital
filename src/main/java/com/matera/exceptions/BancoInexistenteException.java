package com.matera.exceptions;

public class BancoInexistenteException extends RuntimeException{

    public BancoInexistenteException(String message) {
        super(message);
    }
}