package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dtos;

public class QuestionDTO {

    private int id;
    private String theme;
    private String libelle;
    private String reponseAttendue;
    private int difficulte;
    private String explication;
    private String reference;
    private String langue;

    public QuestionDTO() {
    }

    public QuestionDTO(int id, String theme, String libelle, String reponseAttendue,
                       int difficulte, String explication, String reference, String langue) {
        this.id = id;
        this.theme = theme;
        this.libelle = libelle;
        this.reponseAttendue = reponseAttendue;
        this.difficulte = difficulte;
        this.explication = explication;
        this.reference = reference;
        this.langue = langue;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getReponseAttendue() { return reponseAttendue; }
    public void setReponseAttendue(String reponseAttendue) { this.reponseAttendue = reponseAttendue; }

    public int getDifficulte() { return difficulte; }
    public void setDifficulte(int difficulte) { this.difficulte = difficulte; }

    public String getExplication() { return explication; }
    public void setExplication(String explication) { this.explication = explication; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }
}