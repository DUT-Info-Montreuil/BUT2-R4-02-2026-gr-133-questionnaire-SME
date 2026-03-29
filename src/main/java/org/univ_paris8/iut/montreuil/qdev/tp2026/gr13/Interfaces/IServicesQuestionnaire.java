package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.StatistiqueQuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.*;

import java.util.List;
import java.util.Map;

public interface IServicesQuestionnaire {

    List<QuestionnaireDTO> chargerFichier(String cheminFichier)
            throws FichierIntrouvableException, FichierCorrompuException;

    List<QuestionnaireDTO> fournirListeQuestionnaires()
            throws AucunQuestionnaireException;

    QuestionnaireDTO fournirUnQuestionnaire(int idQuestionnaire)
            throws QuestionnaireIntrouvableException;

    void majStatQuestions(int idQuestionnaire, Map<Integer, Boolean> resultats)
            throws QuestionnaireIntrouvableException, DonneesInvalidesException;

    StatistiqueQuestionnaireDTO fournirStatsQuestions(int idQuestionnaire)
            throws QuestionnaireIntrouvableException, AucunePartieJoueeException;
}
