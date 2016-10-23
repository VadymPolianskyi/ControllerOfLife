package com.polyanski.vadym.ua.controlerException.killException;

import java.io.IOException;

/**
 * Created by vadym on 10.08.2016.
 */
public class KillRungingException extends IOException {

    public KillRungingException() {
    }

    public KillRungingException(String message) {
        super(message);
    }

    public KillRungingException(String message, Throwable cause) {
        super(message, cause);
    }

    public KillRungingException(Throwable cause) {
        super(cause);
    }
}
