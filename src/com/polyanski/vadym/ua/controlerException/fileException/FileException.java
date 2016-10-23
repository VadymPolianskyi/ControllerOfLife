package com.polyanski.vadym.ua.controlerException.fileException;

import java.io.IOException;

/**
 * Created by vadym on 08.09.2016.
 */
public class FileException extends IOException{
    public FileException() {}

    public FileException(Throwable cause) {super(cause);}

    public FileException(String message) {super(message);}

    public FileException(String message, Throwable cause) {super(message, cause);}
}
