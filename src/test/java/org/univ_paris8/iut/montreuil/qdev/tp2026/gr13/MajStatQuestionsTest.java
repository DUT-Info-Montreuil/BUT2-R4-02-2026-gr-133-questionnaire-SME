package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.StatistiqueQuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.DonneesInvalidesException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.QuestionnaireIntrouvableException;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MajStatQuestionsTest {

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
    void majStat_resultatsValides_compteursIncrementes() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> resultats = new HashMap<>();
        resultats.put(1, true);
        resultats.put(2, false);
        resultats.put(3, true);

        service.majStatQuestions(1, resultats);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);
        assertEquals(1, stats.getNbPartiesJouees());
    }

    @Test
    void majStat_mixBonnesMauvaises_compteursCoherents() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> resultats = new HashMap<>();
        resultats.put(1, true);
        resultats.put(2, true);
        resultats.put(3, false);

        service.majStatQuestions(1, resultats);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);
        assertEquals(1, stats.getNbPartiesJouees());
        assertEquals(1.0, stats.getTauxReussiteMeilleure(), 1e-9);
        assertEquals(2, stats.getMeilleureQuestion().getNumQuestion());
        assertEquals(0.0, stats.getTauxReussitePire(), 1e-9);
        assertEquals(3, stats.getPireQuestion().getNumQuestion());
    }

    @Test
    void majStat_appelsMultiples_compteursCumules() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> uneFois = new HashMap<>();
        uneFois.put(1, true);

        service.majStatQuestions(1, uneFois);
        service.majStatQuestions(1, uneFois);
        service.majStatQuestions(1, uneFois);

        StatistiqueQuestionnaireDTO stats = service.fournirStatsQuestions(1);
        assertEquals(3, stats.getNbPartiesJouees());
    }

    @Test
    void majStat_resultatsNull_leveDonneesInvalidesException() throws Exception {
        chargerFichierValide();

        assertThrows(DonneesInvalidesException.class, () -> service.majStatQuestions(1, null));
    }

    @Test
    void majStat_resultatsVides_leveDonneesInvalidesException() throws Exception {
        chargerFichierValide();

        assertThrows(DonneesInvalidesException.class, () -> service.majStatQuestions(1, new HashMap<>()));
    }

    @Test
    void majStat_questionnaireInexistant_leveQuestionnaireIntrouvableException() throws Exception {
        chargerFichierValide();
        Map<Integer, Boolean> resultats = Map.of(1, true);

        assertThrows(QuestionnaireIntrouvableException.class, () -> service.majStatQuestions(999, resultats));
    }
}
