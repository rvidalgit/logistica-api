package com.rodrigovidal.logisticaapi.exception;

import java.io.Serializable;

public class NegocioException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5229060087317114999L;

    public NegocioException(String message) {
        super(message);
    }
}
