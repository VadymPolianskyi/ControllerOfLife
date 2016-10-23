package com.polyanski.vadym.ua.controlerException.fileException;

/**
 * Created by vadym on 23.08.2016.
 */
public class EmptyTimeException extends IllegalStateException {

//    private static final long serialVersionUID = 73243233345373893L;

    public EmptyTimeException() {}

    public EmptyTimeException(Throwable cause) {super(cause);}

    public EmptyTimeException(String message) {super(message);}

    public EmptyTimeException(String message, Throwable cause) {super(message, cause);}
}
