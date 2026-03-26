package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto;

public class StatistiqueQuestionnaireDTO {

    private int idQuestionnaire;
    private int nbPartiesJouees;
    private QuestionDTO meilleureQuestion;
    private double tauxReussiteMeilleure;
    private QuestionDTO pireQuestion;
    private double tauxReussitePire;

    public StatistiqueQuestionnaireDTO() {
    }

    public StatistiqueQuestionnaireDTO(int idQuestionnaire, int nbPartiesJouees,
                                       QuestionDTO meilleureQuestion, double tauxReussiteMeilleure,
                                       QuestionDTO pireQuestion, double tauxReussitePire) {
        this.idQuestionnaire = idQuestionnaire;
        this.nbPartiesJouees = nbPartiesJouees;
        this.meilleureQuestion = meilleureQuestion;
        this.tauxReussiteMeilleure = tauxReussiteMeilleure;
        this.pireQuestion = pireQuestion;
        this.tauxReussitePire = tauxReussitePire;
    }

    public int getIdQuestionnaire() { return idQuestionnaire; }
    public void setIdQuestionnaire(int idQuestionnaire) { this.idQuestionnaire = idQuestionnaire; }

    public int getNbPartiesJouees() { return nbPartiesJouees; }
    public void setNbPartiesJouees(int nbPartiesJouees) { this.nbPartiesJouees = nbPartiesJouees; }

    public QuestionDTO getMeilleureQuestion() { return meilleureQuestion; }
    public void setMeilleureQuestion(QuestionDTO meilleureQuestion) { this.meilleureQuestion = meilleureQuestion; }

    public double getTauxReussiteMeilleure() { return tauxReussiteMeilleure; }
    public void setTauxReussiteMeilleure(double tauxReussiteMeilleure) { this.tauxReussiteMeilleure = tauxReussiteMeilleure; }

    public QuestionDTO getPireQuestion() { return pireQuestion; }
    public void setPireQuestion(QuestionDTO pireQuestion) { this.pireQuestion = pireQuestion; }

    public double getTauxReussitePire() { return tauxReussitePire; }
    public void setTauxReussitePire(double tauxReussitePire) { this.tauxReussitePire = tauxReussitePire; }
}
