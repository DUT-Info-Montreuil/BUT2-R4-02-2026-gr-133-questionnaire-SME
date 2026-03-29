package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.QuestionnaireIntrouvableException;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FournirUnQuestionnaireTest {

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
    void fournirUn_idExistant_retourneQuestionnaireComplet() throws Exception {
        chargerFichierValide();

        QuestionnaireDTO q = service.fournirUnQuestionnaire(1);

        assertNotNull(q);
        assertEquals(1, q.getIdQuestionnaire());
        assertEquals(3, q.getQuestions().size());
        Set<Integer> numeros = q.getQuestions().stream().map(QuestionDTO::getNumQuestion).collect(Collectors.toSet());
        assertEquals(Set.of(1, 2, 3), numeros);
    }

    @Test
    void fournirUn_decompteParDifficulte_correct() throws Exception {
        chargerFichierValide();

        QuestionnaireDTO q = service.fournirUnQuestionnaire(1);

        assertEquals(1, q.getNbQuestionsSimples());
        assertEquals(1, q.getNbQuestionsIntermediaires());
        assertEquals(1, q.getNbQuestionsExpertes());
    }

    @Test
    void fournirUn_idInexistant_leveQuestionnaireIntrouvableException() throws Exception {
        chargerFichierValide();

        assertThrows(QuestionnaireIntrouvableException.class, () -> service.fournirUnQuestionnaire(999));
    }
}
