package com.polyanski.vadym.ua.controlerException.killException;

/**
 * Created by vadym on 10.08.2016.
 */
public class KillException extends Exception {
    //    private static final long serialVersionUID = 5473892587564375374L;

    public KillException() {}

    public KillException(String message) {super(message);}

    public KillException(Throwable cause) {super(cause);}

    public KillException(String message, Throwable cause) {super(message, cause);}

    public KillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
