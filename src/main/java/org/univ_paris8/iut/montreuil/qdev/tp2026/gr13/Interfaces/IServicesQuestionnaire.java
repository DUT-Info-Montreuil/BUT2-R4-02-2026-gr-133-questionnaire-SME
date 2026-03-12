package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.CsvInexistantException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.DonneeCorrompueException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.NombreDeQuestionsInsuffisantException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.QuestionnaireInexistantException;

import java.util.ArrayList;
import java.util.List;

public interface IServicesQuestionnaire {

    /**
     * Cas d'utilisation 1 : Charger les questionnaires depuis un fichier CSV.
     * @param chemin Le chemin vers le fichier CSV.
     * @return La liste des questionnaires chargés.
     * @throws CsvInexistantException si le fichier CSV est introuvable.
     * @throws DonneeCorrompueException si le fichier CSV contient des données invalides.
     */
    ArrayList<QuestionnaireDTO> chargerQuestionnaires(String chemin)
            throws CsvInexistantException, DonneeCorrompueException;

    /**
     * Fournir la liste des questionnaires disponibles.
     * @param chemin Le chemin vers le fichier CSV.
     * @throws CsvInexistantException si le fichier CSV est introuvable.
     * @throws DonneeCorrompueException si le fichier CSV contient des données invalides.
     */
    void fournirListQuestionnaires(String chemin)
            throws CsvInexistantException, DonneeCorrompueException;

    /**
     * Cas d'utilisation 2 : Obtenir une série de 10 questions tirées au hasard et sans doublon.
     * @param questionnaire Le questionnaire contenant toutes les questions chargées.
     * @return Une liste de 10 questions aléatoires.
     * @throws NombreDeQuestionsInsuffisantException si le questionnaire contient moins de 10 questions.
     */
    List<QuestionDTO> obtenirQuestionsAleatoires(QuestionnaireDTO questionnaire)
            throws NombreDeQuestionsInsuffisantException;

    /**
     * Cas d'utilisation 3 : Vérifier que la réponse est valide (insensible à la casse et aux espaces).
     * @param reponseUtilisateur La réponse tapée par le joueur.
     * @param questionActive La question en cours.
     * @return true si la réponse est correcte, false sinon.
     */
    boolean verifierReponse(String reponseUtilisateur, QuestionDTO questionActive);

    /**
     * Fournir un questionnaire par son identifiant.
     * @param id L'identifiant du questionnaire recherché.
     * @param questionnaires La liste des questionnaires chargés.
     * @return Le questionnaire correspondant à l'identifiant.
     * @throws QuestionnaireInexistantException si aucun questionnaire ne correspond à l'identifiant.
     */
    QuestionnaireDTO fournirUnQuestionnaire(int id, List<QuestionnaireDTO> questionnaires)
            throws QuestionnaireInexistantException;

}