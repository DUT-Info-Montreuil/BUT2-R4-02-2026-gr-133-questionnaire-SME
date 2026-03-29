package org.univ_paris8.iut.montreuil.qdev.tp2026.gr13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.entites.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.utils.exceptions.AucunQuestionnaireException;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FournirListeQuestionnairesTest {

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
    void fournirListe_questionnairesCharges_retourneListe() throws Exception {
        chargerFichierValide();

        List<QuestionnaireDTO> liste = service.fournirListeQuestionnaires();

        assertEquals(1, liste.size());
    }

    @Test
    void fournirListe_decompteParDifficulte_correct() throws Exception {
        chargerFichierValide();

        QuestionnaireDTO q = service.fournirListeQuestionnaires().get(0);

        assertEquals(1, q.getNbQuestionsSimples());
        assertEquals(1, q.getNbQuestionsIntermediaires());
        assertEquals(1, q.getNbQuestionsExpertes());
    }

    @Test
    void fournirListe_aucunQuestionnaire_leveAucunQuestionnaireException() {
        assertThrows(AucunQuestionnaireException.class, () -> service.fournirListeQuestionnaires());
    }
}
