package net.queencoder.jobsearchapp.exceptions;

import lombok.Getter;

@Getter
public class DuplicateKeyException extends Exception{
    public DuplicateKeyException(String message) {
        super(message);
    }
}
