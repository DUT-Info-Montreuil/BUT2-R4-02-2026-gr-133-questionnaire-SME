package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.StatistiqueQuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.*;

import java.util.List;
import java.util.Map;

public interface IServicesQuestionnaire {

    List<QuestionnaireDTO> chargerQuestionnaires(String chemin)
            throws CsvInexistantException, DonneeCorrompueException;

    List<QuestionnaireDTO> fournirListeQuestionnaires()
            throws AucunQuestionnaireException;

    QuestionnaireDTO fournirUnQuestionnaire(int idQuestionnaire)
            throws QuestionnaireInexistantException;

    List<QuestionDTO> obtenirQuestionsAleatoires(QuestionnaireDTO questionnaire)
            throws NombreDeQuestionsInsuffisantException;

    boolean verifierReponse(String reponseUtilisateur, QuestionDTO questionActive);

    void majStatQuestions(int idQuestionnaire, Map<Integer, Boolean> resultats)
            throws QuestionnaireInexistantException, DonneesInvalidesException;

    StatistiqueQuestionnaireDTO fournirStatsQuestions(int idQuestionnaire)
            throws QuestionnaireInexistantException, AucunePartieJoueeException;
}
