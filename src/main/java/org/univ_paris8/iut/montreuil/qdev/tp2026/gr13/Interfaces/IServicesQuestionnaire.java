package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.Interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dtos.QuestionnaireDTO;

import java.util.ArrayList;

public interface IServicesQuestionnaire {

    public ArrayList<QuestionnaireDTO> chargerQuestionnaires(String chemin);

    public void fournirListQuestionnaires(String chemin);

}
