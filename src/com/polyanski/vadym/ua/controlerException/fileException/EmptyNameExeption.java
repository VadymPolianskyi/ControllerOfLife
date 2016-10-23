package com.polyanski.vadym.ua.controlerException.fileException;

/**
 * Created by vadym on 10.08.2016.
 */
public class EmptyNameExeption extends IllegalStateException {

//    private static final long serialVersionUID = 4724325453345373893L;

    public EmptyNameExeption() {}

    public EmptyNameExeption(Throwable cause) {super(cause);}

    public EmptyNameExeption(String message) {super(message);}

    public EmptyNameExeption(String message, Throwable cause) {super(message, cause);}
}
