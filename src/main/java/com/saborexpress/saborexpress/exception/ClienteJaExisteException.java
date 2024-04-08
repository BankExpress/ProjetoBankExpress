package com.saborexpress.saborexpress.exception;

public class ClienteJaExisteException extends RuntimeException{
    public ClienteJaExisteException(final String msg){
        super(msg);
    }
}
