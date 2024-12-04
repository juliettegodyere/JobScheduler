package net.queencoder.jobsearchapp.exceptions;

import lombok.Getter;

@Getter
public class DataIntegrityViolationException extends Exception {
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}

