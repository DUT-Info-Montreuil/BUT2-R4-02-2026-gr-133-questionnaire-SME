package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.StatistiqueQuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.AucunePartieJoueeException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.QuestionnaireIntrouvableException;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FournirStatsQuestionsTest {

    private ServicesQuestionnaireImpl service;

    @BeforeEach
    void setUp() {
        service = new ServicesQuestionnaireImpl();
    }

    private String cheminRessource(String nom) throws Exception {
        var url = getClass().getClassLoader().getResource(nom);
        assertNotNull(url, "Ressource introuvable : " + nom);
        return Path.of(url.toURI()).toAbsolutePath().toString();
    }

    private void chargerFichierValide() throws Exception {
        service.chargerFichier(cheminRessource("questionsQuizz_test_valide.csv"));
    }

    @Test
    void fournirStats_partiesJouees_statsCorrectes() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> resultats = new HashMap<>();
        resultats.put(1, true);
        resultats.put(2, false);
        resultats.put(3, true);
        service.majStatQuestions(1, resultats);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);

        assertEquals(1, stats.getNbPartiesJouees());
        assertEquals(3, stats.getMeilleureQuestion().getNumQuestion());
        assertEquals(1.0, stats.getTauxReussiteMeilleure(), 1e-9);
        assertEquals(2, stats.getPireQuestion().getNumQuestion());
        assertEquals(0.0, stats.getTauxReussitePire(), 1e-9);
    }

    @Test
    void fournirStats_departageMeilleure_difficultePlusGrande() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> toutBon = Map.of(1, true, 2, true, 3, true);
        service.majStatQuestions(1, toutBon);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);

        assertEquals(3, stats.getMeilleureQuestion().getDifficulte());
        assertEquals(3, stats.getMeilleureQuestion().getNumQuestion());
    }

    @Test
    void fournirStats_departageMeilleure_memeDifficulte_plusPosee() throws Exception {
        service.chargerFichier(cheminRessource("questionsQuizz_test_departage_meme_difficulte.csv"));
        service.majStatQuestions(1, Map.of(1, true, 2, true));
        service.majStatQuestions(1, Map.of(1, true));

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);

        assertEquals(1, stats.getMeilleureQuestion().getNumQuestion());
        assertEquals(1.0, stats.getTauxReussiteMeilleure(), 1e-9);
    }

    @Test
    void fournirStats_departagePire_difficultePlusFaible() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> resultats = new HashMap<>();
        resultats.put(1, false);
        resultats.put(3, false);
        service.majStatQuestions(1, resultats);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);

        assertEquals(1, stats.getPireQuestion().getNumQuestion());
        assertEquals(1, stats.getPireQuestion().getDifficulte());
        assertEquals(0.0, stats.getTauxReussitePire(), 1e-9);
    }

    @Test
    void fournirStats_aucunePartieJouee_leveAucunePartieJoueeException() throws Exception {
        chargerFichierValide();

        assertThrows(AucunePartieJoueeException.class, () -> service.fournirStatsQuestions(1));
    }

    @Test
    void fournirStats_questionnaireInexistant_leveQuestionnaireIntrouvableException() throws Exception {
        chargerFichierValide();

        assertThrows(QuestionnaireIntrouvableException.class, () -> service.fournirStatsQuestions(999));
    }
}
