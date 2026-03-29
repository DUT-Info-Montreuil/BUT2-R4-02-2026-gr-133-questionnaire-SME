package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions;

public class FichierCorrompuException extends Exception {
    public FichierCorrompuException(String message) {
        super(message);
    }

    public FichierCorrompuException(String message, Throwable cause) {
        super(message, cause);
    }
}
