# Prompt pour l'équipe de test — Projet SME Questionnaire

## Contexte

Tu es un développeur Java chargé d'écrire les **tests unitaires** (JUnit 5) pour le projet SME Questionnaire.
Le code de développement est terminé. Tu dois maintenant tester chaque fonctionnalité.

### Framework de test
- **JUnit 5** (déjà dans le pom.xml)
- Les tests se placent dans `src/test/java/org/univ_paris8/iut/montreuil/qdev/tp2026/gr13/`

### Package de base
```
org.univ_paris8.iut.montreuil.qdev.tp2026.gr13
```

### Classe à tester
```
org.univ_paris8.iut.montreuil.qdev.tp2026.gr13.impls.ServicesQuestionnaireImpl
```

Cette classe implémente l'interface `IServicesQuestionnaire` et contient 7 méthodes publiques.

---

## Plan des branches de test

| # | Branche | Méthode testée | Classe de test à créer |
|---|---------|----------------|------------------------|
| 1 | `feature/testChargerQuestionnaires` | `chargerQuestionnaires(String chemin)` | `ChargerQuestionnairesTest.java` |
| 2 | `feature/testFournirListe` | `fournirListeQuestionnaires()` | `FournirListeQuestionnairesTest.java` |
| 3 | `feature/testFournirQuest` | `fournirUnQuestionnaire(int id)` | `FournirUnQuestionnaireTest.java` |
| 4 | `feature/testQuestionsAleatoires` | `obtenirQuestionsAleatoires(QuestionnaireDTO)` | `ObtenirQuestionsAleatoiresTest.java` |
| 5 | `feature/testVerifierReponse` | `verifierReponse(String, QuestionDTO)` | `VerifierReponseTest.java` |
| 6 | `feature/testMajStats` | `majStatQuestions(int, Map<Integer, Boolean>)` | `MajStatQuestionsTest.java` |
| 7 | `feature/testFournirStats` | `fournirStatsQuestions(int)` | `FournirStatsQuestionsTest.java` |

Chaque branche de test doit être créée depuis `feature/devChargerFichier` (qui contient tout le code dev).

---

## Détail des tests à écrire par branche

### 1. `feature/testChargerQuestionnaires` — ChargerQuestionnairesTest.java

La méthode `chargerQuestionnaires(String chemin)` charge un fichier CSV et retourne une `List<QuestionnaireDTO>`.

**Fichiers CSV de test à créer dans `src/test/resources/` :**
- `questionsValides.csv` — Fichier CSV valide avec au moins 2 questionnaires et plusieurs questions
- `questionsCorrompues.csv` — Fichier avec des lignes ayant un nombre de colonnes incorrect (pas 9)
- `questionsValeursInvalides.csv` — Fichier avec des valeurs numériques invalides (ex: "abc" au lieu d'un int)

**Séparateur CSV :** tabulation (`\t`). Le code supporte aussi `;` en fallback.

**Format CSV (9 colonnes, pas d'en-tête) :**
```
idQuestionnaire	libelléQuestionnaire	numQuestion	langue	libelléQuestion	réponse	difficulté	explication	référence
```

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testChargerFichierValide` | Charger un CSV valide | Retourne une liste non vide de QuestionnaireDTO, vérifier le nombre de questionnaires, les ids, les libellés, le nombre de questions par questionnaire |
| `testChargerFichierInexistant` | Passer un chemin vers un fichier qui n'existe pas | Lève `CsvInexistantException` |
| `testChargerFichierCorrompu` | Charger un CSV avec un mauvais nombre de colonnes | Lève `DonneeCorrompueException` |
| `testChargerFichierValeursInvalides` | Charger un CSV avec des valeurs numériques invalides | Lève `DonneeCorrompueException` |
| `testChargerFichierCompteursDifficulte` | Charger un CSV valide et vérifier les compteurs | `nbQuestionsSimples`, `nbQuestionsIntermediaires`, `nbQuestionsExpertes` sont corrects |
| `testChargerFichierRegroupement` | Charger un CSV avec plusieurs questionnaires | Les questions sont bien regroupées par `idQuestionnaire` |

---

### 2. `feature/testFournirListe` — FournirListeQuestionnairesTest.java

La méthode `fournirListeQuestionnaires()` retourne la liste des questionnaires chargés en mémoire.

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testFournirListeApresChargement` | Charger un CSV puis appeler la méthode | Retourne la même liste que celle retournée par chargerQuestionnaires |
| `testFournirListeSansChargement` | Appeler la méthode sans avoir chargé de CSV | Lève `AucunQuestionnaireException` |
| `testFournirListeContenuCorrect` | Vérifier le contenu des DTOs retournés | Chaque QuestionnaireDTO a les bons champs (id, libellé, langue, compteurs) |

---

### 3. `feature/testFournirQuest` — FournirUnQuestionnaireTest.java

La méthode `fournirUnQuestionnaire(int idQuestionnaire)` retourne un questionnaire par son id.

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testFournirQuestionnaireExistant` | Demander un questionnaire avec un id valide | Retourne le bon QuestionnaireDTO avec toutes ses questions |
| `testFournirQuestionnaireInexistant` | Demander un questionnaire avec un id qui n'existe pas | Lève `QuestionnaireInexistantException` |
| `testFournirQuestionnaireVerifierQuestions` | Vérifier les questions du DTO retourné | La liste de QuestionDTO contient les bonnes questions avec les bons champs |

---

### 4. `feature/testQuestionsAleatoires` — ObtenirQuestionsAleatoiresTest.java

La méthode `obtenirQuestionsAleatoires(QuestionnaireDTO questionnaire)` retourne 10 questions tirées au hasard.

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testObtenirQuestionsAleatoires10` | Passer un questionnaire avec 10+ questions | Retourne exactement 10 questions |
| `testQuestionsAleatoiresPasDeDoublon` | Vérifier l'unicité des questions retournées | Aucune question en double (vérifier les numQuestion) |
| `testQuestionsAleatoiresInsuffisantes` | Passer un questionnaire avec moins de 10 questions | Lève `NombreDeQuestionsInsuffisantException` |
| `testQuestionsAleatoiresOrdreVariable` | Appeler la méthode plusieurs fois | L'ordre des questions varie (aléatoire) |

---

### 5. `feature/testVerifierReponse` — VerifierReponseTest.java

La méthode `verifierReponse(String reponseUtilisateur, QuestionDTO questionActive)` compare la réponse (case-insensitive).

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testReponseCorrecte` | Réponse exacte | `true` |
| `testReponseCaseDifferente` | "tee" vs "Tee" | `true` (case-insensitive) |
| `testReponseAvecEspaces` | " Tee " vs "Tee" | `true` (trim) |
| `testReponseIncorrecte` | "mauvaise" vs "Tee" | `false` |
| `testReponseNull` | Passer null comme réponse utilisateur | `false` |

---

### 6. `feature/testMajStats` — MajStatQuestionsTest.java

La méthode `majStatQuestions(int idQuestionnaire, Map<Integer, Boolean> resultats)` met à jour les statistiques des questions.

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testMajStatsResultatsValides` | Passer des résultats valides | `nbFoisPosee` et `nbBonnesReponses` incrémentés correctement, `nbPartiesJouees` incrémenté de 1 |
| `testMajStatsResultatsNull` | Passer null | Lève `DonneesInvalidesException` |
| `testMajStatsResultatsVides` | Passer une map vide | Lève `DonneesInvalidesException` |
| `testMajStatsQuestionnaireInexistant` | Passer un id de questionnaire inexistant | Lève `QuestionnaireInexistantException` |
| `testMajStatsPlusieursParties` | Appeler la méthode plusieurs fois | Les compteurs s'accumulent correctement |

**Note :** Pour vérifier les stats après majStatQuestions, utiliser `fournirStatsQuestions()` qui expose les résultats via `StatistiqueQuestionnaireDTO`.

---

### 7. `feature/testFournirStats` — FournirStatsQuestionsTest.java

La méthode `fournirStatsQuestions(int idQuestionnaire)` retourne les statistiques avec meilleure/pire question.

**Tests à écrire :**

| Test | Description | Résultat attendu |
|------|-------------|------------------|
| `testFournirStatsApresParties` | Jouer des parties puis demander les stats | Retourne un `StatistiqueQuestionnaireDTO` avec les bons taux |
| `testFournirStatsAucunePartie` | Demander les stats sans partie jouée | Lève `AucunePartieJoueeException` |
| `testFournirStatsQuestionnaireInexistant` | Demander les stats d'un id inexistant | Lève `QuestionnaireInexistantException` |
| `testFournirStatsMeilleureQuestion` | Jouer des parties avec une question à 100% de réussite | La meilleure question est celle avec le taux le + élevé |
| `testFournirStatsPireQuestion` | Jouer des parties avec une question à 0% de réussite | La pire question est celle avec le taux le + faible |
| `testDepartageMemeTauxMeilleure` | Deux questions avec le même taux de réussite | Départage : difficulté la + grande → la + posée → première dans l'ordre du fichier |
| `testDepartageMemeTauxPire` | Deux questions avec le même taux de réussite | Départage : difficulté la + faible → la + posée → première dans l'ordre du fichier |

---

## Règles importantes pour les tests

1. **Chaque branche de test** doit être créée depuis `feature/devChargerFichier`
2. **Un fichier CSV de test valide** est nécessaire dans `src/test/resources/` pour la majorité des tests (car il faut d'abord charger des questionnaires)
3. **Instancier le service** avec `new ServicesQuestionnaireImpl()` au début de chaque classe de test
4. **Utiliser `@BeforeEach`** pour charger le CSV avant les tests qui en ont besoin
5. **Ne pas modifier le code de développement** — écrire uniquement des tests
6. **Ne pas faire de push sans validation** — vérifier que `mvn test` passe avant de committer
7. **Committer en français** avec un message clair décrivant les tests ajoutés
