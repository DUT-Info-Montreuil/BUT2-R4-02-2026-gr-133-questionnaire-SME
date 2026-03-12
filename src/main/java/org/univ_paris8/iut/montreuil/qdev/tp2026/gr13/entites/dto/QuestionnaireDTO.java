package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {

    private int id;
    private String titre;
    private List<QuestionDTO> listeCompleteQuestions;

    public QuestionnaireDTO() {
        this.listeCompleteQuestions = new ArrayList<>();
    }

    public QuestionnaireDTO(int id, String titre, List<QuestionDTO> listeCompleteQuestions) {
        this.id = id;
        this.titre = titre;
        this.listeCompleteQuestions = listeCompleteQuestions;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public List<QuestionDTO> getListeCompleteQuestions() { return listeCompleteQuestions; }
    public void setListeCompleteQuestions(List<QuestionDTO> listeCompleteQuestions) {
        this.listeCompleteQuestions = listeCompleteQuestions;
    }
}