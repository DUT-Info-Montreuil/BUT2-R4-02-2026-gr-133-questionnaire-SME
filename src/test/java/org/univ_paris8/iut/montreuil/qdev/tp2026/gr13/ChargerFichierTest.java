package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.FichierCorrompuException;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.FichierIntrouvableException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChargerFichierTest {

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

    @Test
    void chargerFichier_fichierValide_retourneQuestionnaires() throws Exception {
        List<QuestionnaireDTO> liste = service.chargerFichier(cheminRessource("questionsQuizz_test_valide.csv"));

        assertNotNull(liste);
        assertEquals(1, liste.size());
        QuestionnaireDTO q = liste.get(0);
        assertEquals(1, q.getIdQuestionnaire());
        assertEquals("Sport niv 1", q.getLibelleQuestionnaire());
        assertEquals("fr", q.getLangue());
        assertEquals(3, q.getQuestions().size());
    }

    @Test
    void chargerFichier_plusieursQuestionnaires_tousCharges() throws Exception {
        List<QuestionnaireDTO> liste = service.chargerFichier(cheminRessource("questionsQuizz_test_multi_lang.csv"));

        assertEquals(2, liste.size());
    }

    @Test
    void chargerFichier_languesDifferentes_correctementStockees() throws Exception {
        List<QuestionnaireDTO> liste = service.chargerFichier(cheminRessource("questionsQuizz_test_multi_lang.csv"));

        assertEquals("fr", liste.get(0).getLangue());
        assertEquals("en", liste.get(1).getLangue());
    }

    @Test
    void chargerFichier_fichierInexistant_leveFichierIntrouvableException() {
        assertThrows(FichierIntrouvableException.class,
                () -> service.chargerFichier(Paths.get("chemin", "inexistant", "fichier.csv").toString()));
    }

    @Test
    void chargerFichier_fichierCorrompu_leveFichierCorrompuException() throws Exception {
        assertThrows(FichierCorrompuException.class,
                () -> service.chargerFichier(cheminRessource("questionsQuizz_test_corrompu.csv")));
    }

    @Test
    void chargerFichier_difficulteInvalide_leveFichierCorrompuException() throws Exception {
        assertThrows(FichierCorrompuException.class,
                () -> service.chargerFichier(cheminRessource("questionsQuizz_test_difficulte_invalide.csv")));
    }

    @Test
    void chargerFichier_fichierVide_leveFichierCorrompuException() throws Exception {
        assertThrows(FichierCorrompuException.class,
                () -> service.chargerFichier(cheminRessource("questionsQuizz_test_vide.csv")));
    }
}
