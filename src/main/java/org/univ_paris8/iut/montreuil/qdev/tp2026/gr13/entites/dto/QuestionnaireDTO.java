package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {

    private int idQuestionnaire;
    private String libelleQuestionnaire;
    private String langue;
    private List<QuestionDTO> questions;
    private int nbQuestionsSimples;
    private int nbQuestionsIntermediaires;
    private int nbQuestionsExpertes;

    public QuestionnaireDTO() {
        this.questions = new ArrayList<>();
    }

    public QuestionnaireDTO(int idQuestionnaire, String libelleQuestionnaire, String langue,
                            List<QuestionDTO> questions, int nbQuestionsSimples,
                            int nbQuestionsIntermediaires, int nbQuestionsExpertes) {
        this.idQuestionnaire = idQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.langue = langue;
        this.questions = questions;
        this.nbQuestionsSimples = nbQuestionsSimples;
        this.nbQuestionsIntermediaires = nbQuestionsIntermediaires;
        this.nbQuestionsExpertes = nbQuestionsExpertes;
    }

    public int getIdQuestionnaire() { return idQuestionnaire; }
    public void setIdQuestionnaire(int idQuestionnaire) { this.idQuestionnaire = idQuestionnaire; }

    public String getLibelleQuestionnaire() { return libelleQuestionnaire; }
    public void setLibelleQuestionnaire(String libelleQuestionnaire) { this.libelleQuestionnaire = libelleQuestionnaire; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }

    public int getNbQuestionsSimples() { return nbQuestionsSimples; }
    public void setNbQuestionsSimples(int nbQuestionsSimples) { this.nbQuestionsSimples = nbQuestionsSimples; }

    public int getNbQuestionsIntermediaires() { return nbQuestionsIntermediaires; }
    public void setNbQuestionsIntermediaires(int nbQuestionsIntermediaires) { this.nbQuestionsIntermediaires = nbQuestionsIntermediaires; }

    public int getNbQuestionsExpertes() { return nbQuestionsExpertes; }
    public void setNbQuestionsExpertes(int nbQuestionsExpertes) { this.nbQuestionsExpertes = nbQuestionsExpertes; }
}
