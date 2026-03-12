package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions;

public class NombreDeQuestionsInsuffisantException extends Exception {

    public NombreDeQuestionsInsuffisantException(String message) {
        super(message);
    }

    public NombreDeQuestionsInsuffisantException(String message, Throwable cause) {
        super(message, cause);
    }
}