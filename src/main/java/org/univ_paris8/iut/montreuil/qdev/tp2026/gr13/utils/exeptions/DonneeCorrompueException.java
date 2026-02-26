package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exeptions;

public class DonneeCorrompueException extends Exception {

    public DonneeCorrompueException(String message) {
        super(message);
    }

    public DonneeCorrompueException(String message, Throwable cause) {
        super(message, cause);
    }
}