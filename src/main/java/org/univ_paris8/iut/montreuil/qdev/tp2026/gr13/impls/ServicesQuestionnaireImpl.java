package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls;

import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.bo.QuestionBO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.bo.QuestionnaireBO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.StatistiqueQuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.interfaces.IServicesQuestionnaire;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ServicesQuestionnaireImpl implements IServicesQuestionnaire {

    private List<QuestionnaireBO> questionnairesBO = new ArrayList<>();

    @Override
    public List<QuestionnaireDTO> chargerQuestionnaires(String chemin)
            throws CsvInexistantException, DonneeCorrompueException {

        File fichier = new File(chemin);
        if (!fichier.exists()) {
            throw new CsvInexistantException("Le fichier CSV est introuvable : " + chemin);
        }

        Map<Integer, QuestionnaireBO> mapQuestionnaires = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int numLigne = 0;
            while ((ligne = reader.readLine()) != null) {
                numLigne++;
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                String[] colonnes = ligne.split("\t");
                if (colonnes.length != 9) {
                    colonnes = ligne.split(";");
                }
                if (colonnes.length != 9) {
                    throw new DonneeCorrompueException(
                            "Ligne " + numLigne + " : attendu 9 colonnes, trouvé " + colonnes.length);
                }

                int idQuestionnaire;
                int numQuestion;
                int difficulte;
                try {
                    idQuestionnaire = Integer.parseInt(colonnes[0].trim());
                    numQuestion = Integer.parseInt(colonnes[2].trim());
                    difficulte = Integer.parseInt(colonnes[6].trim());
                } catch (NumberFormatException e) {
                    throw new DonneeCorrompueException(
                            "Ligne " + numLigne + " : valeur numérique invalide", e);
                }

                String libelleQuestionnaire = colonnes[1].trim();
                String langue = colonnes[3].trim();
                String libelleQuestion = colonnes[4].trim();
                String reponse = colonnes[5].trim();
                String explication = colonnes[7].trim();
                String reference = colonnes[8].trim();

                QuestionBO questionBO = new QuestionBO(
                        idQuestionnaire, numQuestion, libelleQuestion,
                        reponse, difficulte, explication, reference
                );

                if (!mapQuestionnaires.containsKey(idQuestionnaire)) {
                    QuestionnaireBO questionnaireBO = new QuestionnaireBO(
                            idQuestionnaire, libelleQuestionnaire, langue
                    );
                    mapQuestionnaires.put(idQuestionnaire, questionnaireBO);
                }
                mapQuestionnaires.get(idQuestionnaire).getQuestions().add(questionBO);
            }
        } catch (IOException e) {
            throw new DonneeCorrompueException("Erreur lors de la lecture du fichier : " + chemin, e);
        }

        questionnairesBO = new ArrayList<>(mapQuestionnaires.values());

        List<QuestionnaireDTO> resultat = new ArrayList<>();
        for (QuestionnaireBO bo : questionnairesBO) {
            resultat.add(convertirQuestionnaireBoEnDto(bo));
        }
        return resultat;
    }

    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires() throws AucunQuestionnaireException {
        if (questionnairesBO.isEmpty()) {
            throw new AucunQuestionnaireException("Aucun questionnaire n'est chargé.");
        }
        List<QuestionnaireDTO> resultat = new ArrayList<>();
        for (QuestionnaireBO bo : questionnairesBO) {
            resultat.add(convertirQuestionnaireBoEnDto(bo));
        }
        return resultat;
    }

    @Override
    public QuestionnaireDTO fournirUnQuestionnaire(int idQuestionnaire)
            throws QuestionnaireInexistantException {
        for (QuestionnaireBO bo : questionnairesBO) {
            if (bo.getIdQuestionnaire() == idQuestionnaire) {
                return convertirQuestionnaireBoEnDto(bo);
            }
        }
        throw new QuestionnaireInexistantException(
                "Aucun questionnaire trouvé avec l'id : " + idQuestionnaire);
    }

    @Override
    public List<QuestionDTO> obtenirQuestionsAleatoires(QuestionnaireDTO questionnaire)
            throws NombreDeQuestionsInsuffisantException {
        List<QuestionDTO> toutesQuestions = questionnaire.getQuestions();
        if (toutesQuestions.size() < 10) {
            throw new NombreDeQuestionsInsuffisantException(
                    "Le questionnaire contient seulement " + toutesQuestions.size()
                            + " questions, il en faut au minimum 10.");
        }
        List<QuestionDTO> copie = new ArrayList<>(toutesQuestions);
        Collections.shuffle(copie);
        return copie.subList(0, 10);
    }

    @Override
    public boolean verifierReponse(String reponseUtilisateur, QuestionDTO questionActive) {
        if (reponseUtilisateur == null || questionActive.getReponse() == null) {
            return false;
        }
        return reponseUtilisateur.trim().equalsIgnoreCase(questionActive.getReponse().trim());
    }

    @Override
    public void majStatQuestions(int idQuestionnaire, Map<Integer, Boolean> resultats)
            throws QuestionnaireInexistantException, DonneesInvalidesException {
        if (resultats == null || resultats.isEmpty()) {
            throw new DonneesInvalidesException("Les résultats ne peuvent pas être null ou vides.");
        }

        QuestionnaireBO questionnaire = trouverQuestionnaireBO(idQuestionnaire);

        for (Map.Entry<Integer, Boolean> entry : resultats.entrySet()) {
            int numQuestion = entry.getKey();
            boolean bonneReponse = entry.getValue();

            for (QuestionBO question : questionnaire.getQuestions()) {
                if (question.getNumQuestion() == numQuestion) {
                    question.setNbFoisPosee(question.getNbFoisPosee() + 1);
                    if (bonneReponse) {
                        question.setNbBonnesReponses(question.getNbBonnesReponses() + 1);
                    }
                    break;
                }
            }
        }
        questionnaire.setNbPartiesJouees(questionnaire.getNbPartiesJouees() + 1);
    }

    @Override
    public StatistiqueQuestionnaireDTO fournirStatsQuestions(int idQuestionnaire)
            throws QuestionnaireInexistantException, AucunePartieJoueeException {

        QuestionnaireBO questionnaire = trouverQuestionnaireBO(idQuestionnaire);

        if (questionnaire.getNbPartiesJouees() == 0) {
            throw new AucunePartieJoueeException(
                    "Aucune partie jouée pour le questionnaire id : " + idQuestionnaire);
        }

        QuestionBO meilleure = null;
        double meilleurTaux = -1;
        QuestionBO pire = null;
        double pireTaux = 2;

        for (QuestionBO q : questionnaire.getQuestions()) {
            if (q.getNbFoisPosee() == 0) {
                continue;
            }
            double taux = (double) q.getNbBonnesReponses() / q.getNbFoisPosee();

            if (taux > meilleurTaux
                    || (taux == meilleurTaux && meilleure != null && estMeilleureMeilleure(q, meilleure))) {
                meilleure = q;
                meilleurTaux = taux;
            }

            if (taux < pireTaux
                    || (taux == pireTaux && pire != null && estMeilleurePire(q, pire))) {
                pire = q;
                pireTaux = taux;
            }
        }

        StatistiqueQuestionnaireDTO stats = new StatistiqueQuestionnaireDTO();
        stats.setIdQuestionnaire(idQuestionnaire);
        stats.setNbPartiesJouees(questionnaire.getNbPartiesJouees());
        if (meilleure != null) {
            stats.setMeilleureQuestion(convertirQuestionBoEnDto(meilleure));
            stats.setTauxReussiteMeilleure(meilleurTaux);
        }
        if (pire != null) {
            stats.setPireQuestion(convertirQuestionBoEnDto(pire));
            stats.setTauxReussitePire(pireTaux);
        }
        return stats;
    }

    // --- Méthodes utilitaires privées ---

    private QuestionnaireBO trouverQuestionnaireBO(int idQuestionnaire)
            throws QuestionnaireInexistantException {
        for (QuestionnaireBO bo : questionnairesBO) {
            if (bo.getIdQuestionnaire() == idQuestionnaire) {
                return bo;
            }
        }
        throw new QuestionnaireInexistantException(
                "Aucun questionnaire trouvé avec l'id : " + idQuestionnaire);
    }

    /**
     * Règle de départage meilleure question :
     * difficulté la + grande → la + posée → première dans l'ordre du fichier
     */
    private boolean estMeilleureMeilleure(QuestionBO candidate, QuestionBO actuelle) {
        if (candidate.getDifficulte() != actuelle.getDifficulte()) {
            return candidate.getDifficulte() > actuelle.getDifficulte();
        }
        if (candidate.getNbFoisPosee() != actuelle.getNbFoisPosee()) {
            return candidate.getNbFoisPosee() > actuelle.getNbFoisPosee();
        }
        return false;
    }

    /**
     * Règle de départage pire question :
     * difficulté la + faible → la + posée → première dans l'ordre du fichier
     */
    private boolean estMeilleurePire(QuestionBO candidate, QuestionBO actuelle) {
        if (candidate.getDifficulte() != actuelle.getDifficulte()) {
            return candidate.getDifficulte() < actuelle.getDifficulte();
        }
        if (candidate.getNbFoisPosee() != actuelle.getNbFoisPosee()) {
            return candidate.getNbFoisPosee() > actuelle.getNbFoisPosee();
        }
        return false;
    }

    private QuestionnaireDTO convertirQuestionnaireBoEnDto(QuestionnaireBO bo) {
        List<QuestionDTO> questionsDTO = new ArrayList<>();
        int nbSimples = 0, nbIntermediaires = 0, nbExpertes = 0;

        for (QuestionBO qbo : bo.getQuestions()) {
            questionsDTO.add(convertirQuestionBoEnDto(qbo));
            switch (qbo.getDifficulte()) {
                case 1: nbSimples++; break;
                case 2: nbIntermediaires++; break;
                case 3: nbExpertes++; break;
            }
        }

        return new QuestionnaireDTO(
                bo.getIdQuestionnaire(),
                bo.getLibelleQuestionnaire(),
                bo.getLangue(),
                questionsDTO,
                nbSimples,
                nbIntermediaires,
                nbExpertes
        );
    }

    private QuestionDTO convertirQuestionBoEnDto(QuestionBO bo) {
        return new QuestionDTO(
                bo.getIdQuestionnaire(),
                bo.getNumQuestion(),
                bo.getLibelleQuestion(),
                bo.getReponse(),
                bo.getDifficulte(),
                bo.getExplication(),
                bo.getReference()
        );
    }
}
