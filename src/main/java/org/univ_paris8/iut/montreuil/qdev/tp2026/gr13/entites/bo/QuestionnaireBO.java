package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.bo;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireBO {

    private int idQuestionnaire;
    private String libelleQuestionnaire;
    private String langue;
    private List<QuestionBO> questions;
    private int nbPartiesJouees;

    public QuestionnaireBO() {
        this.questions = new ArrayList<>();
        this.nbPartiesJouees = 0;
    }

    public QuestionnaireBO(int idQuestionnaire, String libelleQuestionnaire, String langue) {
        this.idQuestionnaire = idQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.langue = langue;
        this.questions = new ArrayList<>();
        this.nbPartiesJouees = 0;
    }

    public int getIdQuestionnaire() { return idQuestionnaire; }
    public void setIdQuestionnaire(int idQuestionnaire) { this.idQuestionnaire = idQuestionnaire; }

    public String getLibelleQuestionnaire() { return libelleQuestionnaire; }
    public void setLibelleQuestionnaire(String libelleQuestionnaire) { this.libelleQuestionnaire = libelleQuestionnaire; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public List<QuestionBO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionBO> questions) { this.questions = questions; }

    public int getNbPartiesJouees() { return nbPartiesJouees; }
    public void setNbPartiesJouees(int nbPartiesJouees) { this.nbPartiesJouees = nbPartiesJouees; }
}
