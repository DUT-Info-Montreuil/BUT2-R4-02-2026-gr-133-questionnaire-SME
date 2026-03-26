package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions;

public class DonneesInvalidesException extends Exception {

    public DonneesInvalidesException(String message) {
        super(message);
    }

    public DonneesInvalidesException(String message, Throwable cause) {
        super(message, cause);
    }
}
