package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.bo;

public class QuestionBO {

    private int idQuestionnaire;
    private int numQuestion;
    private String libelleQuestion;
    private String reponse;
    private int difficulte;
    private String explication;
    private String reference;
    private int nbFoisPosee;
    private int nbBonnesReponses;

    public QuestionBO() {
    }

    public QuestionBO(int idQuestionnaire, int numQuestion, String libelleQuestion,
                      String reponse, int difficulte, String explication, String reference) {
        this.idQuestionnaire = idQuestionnaire;
        this.numQuestion = numQuestion;
        this.libelleQuestion = libelleQuestion;
        this.reponse = reponse;
        this.difficulte = difficulte;
        this.explication = explication;
        this.reference = reference;
        this.nbFoisPosee = 0;
        this.nbBonnesReponses = 0;
    }

    public int getIdQuestionnaire() { return idQuestionnaire; }
    public void setIdQuestionnaire(int idQuestionnaire) { this.idQuestionnaire = idQuestionnaire; }

    public int getNumQuestion() { return numQuestion; }
    public void setNumQuestion(int numQuestion) { this.numQuestion = numQuestion; }

    public String getLibelleQuestion() { return libelleQuestion; }
    public void setLibelleQuestion(String libelleQuestion) { this.libelleQuestion = libelleQuestion; }

    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }

    public int getDifficulte() { return difficulte; }
    public void setDifficulte(int difficulte) { this.difficulte = difficulte; }

    public String getExplication() { return explication; }
    public void setExplication(String explication) { this.explication = explication; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public int getNbFoisPosee() { return nbFoisPosee; }
    public void setNbFoisPosee(int nbFoisPosee) { this.nbFoisPosee = nbFoisPosee; }

    public int getNbBonnesReponses() { return nbBonnesReponses; }
    public void setNbBonnesReponses(int nbBonnesReponses) { this.nbBonnesReponses = nbBonnesReponses; }
}
