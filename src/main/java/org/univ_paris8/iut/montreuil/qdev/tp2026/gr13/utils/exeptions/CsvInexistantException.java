package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exeptions;

public class CsvInexistantException extends Exception {

    public CsvInexistantException(String message) {
        super(message);
    }

    public CsvInexistantException(String message, Throwable cause) {
        super(message, cause);
    }
}