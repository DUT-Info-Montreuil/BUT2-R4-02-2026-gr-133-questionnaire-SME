package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions;

public class QuestionnaireInexistantException extends Exception {

    public QuestionnaireInexistantException(String message) {
        super(message);
    }

    public QuestionnaireInexistantException(String message, Throwable cause) {
        super(message, cause);
    }
}