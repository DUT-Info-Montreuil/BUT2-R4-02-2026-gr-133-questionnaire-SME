# Récapitulatif des branches de développement

## Vue d'ensemble

| # | Branche | Statut |
|---|---------|--------|
| 1 | `feature/devEntities` | Pushée |
| 2 | `feature/devExceptions` | Pushée |
| 3 | `feature/devChargerFichier` | Pushée |

---

## Détail par branche

### 1. `feature/devEntities`

Création et correction de toutes les entités du projet (BO et DTO).

**Fichiers créés :**
- `entites/bo/QuestionBO.java` — Objet métier interne avec 9 champs : idQuestionnaire, numQuestion, libelleQuestion, reponse, difficulte, explication, reference, nbFoisPosee, nbBonnesReponses
- `entites/bo/QuestionnaireBO.java` — Objet métier interne avec 5 champs : idQuestionnaire, libelleQuestionnaire, langue, questions (liste de QuestionBO), nbPartiesJouees
- `entites/dto/StatistiqueQuestionnaireDTO.java` — DTO exposé au SINT avec : idQuestionnaire, nbPartiesJouees, meilleureQuestion, tauxReussiteMeilleure, pireQuestion, tauxReussitePire

**Fichiers corrigés :**
- `entites/dto/QuestionDTO.java` — Champs renommés selon le cahier des charges (idQuestionnaire, numQuestion, libelleQuestion, reponse, difficulte, explication, reference)
- `entites/dto/QuestionnaireDTO.java` — Ajout des champs manquants (langue, nbQuestionsSimples, nbQuestionsIntermediaires, nbQuestionsExpertes)

---

### 2. `feature/devExceptions`

Ajout des 3 exceptions métier manquantes.

**Fichiers créés :**
- `utils/exceptions/AucunQuestionnaireException.java` — Levée quand aucun questionnaire n'est chargé en mémoire
- `utils/exceptions/AucunePartieJoueeException.java` — Levée quand on demande les stats sans qu'aucune partie n'ait été jouée
- `utils/exceptions/DonneesInvalidesException.java` — Levée quand les données passées à majStatQuestions sont null ou vides

**Exceptions déjà existantes (4) :**
- `CsvInexistantException`
- `DonneeCorrompueException`
- `NombreDeQuestionsInsuffisantException`
- `QuestionnaireInexistantException`

**Total : 7 exceptions**

---

### 3. `feature/devChargerFichier`

Correction de l'interface et implémentation complète de tous les services.

**Fichiers modifiés :**
- `Interfaces/IServicesQuestionnaire.java` — Interface corrigée avec les 7 méthodes conformes au cahier des charges

**Fichiers créés :**
- `impls/ServicesQuestionnaireImpl.java` — Implémentation complète contenant :

| Méthode | Description |
|---------|-------------|
| `chargerQuestionnaires(String chemin)` | Charge un fichier CSV (séparateur tab ou ;), valide 9 colonnes par ligne, crée les BO, regroupe par idQuestionnaire, retourne la liste des QuestionnaireDTO |
| `fournirListeQuestionnaires()` | Retourne la liste des questionnaires chargés en mémoire |
| `fournirUnQuestionnaire(int id)` | Recherche un questionnaire par son identifiant |
| `obtenirQuestionsAleatoires(QuestionnaireDTO)` | Tire 10 questions au hasard sans doublon |
| `verifierReponse(String, QuestionDTO)` | Compare la réponse de l'utilisateur (case-insensitive, trim) |
| `majStatQuestions(int, Map<Integer, Boolean>)` | Met à jour nbFoisPosee et nbBonnesReponses pour chaque question, incrémente nbPartiesJouees |
| `fournirStatsQuestions(int)` | Calcule et retourne les stats avec règles de départage (meilleure/pire question) |

**Méthodes utilitaires privées :**
- `convertirQuestionnaireBoEnDto()` — Conversion BO → DTO avec comptage par difficulté
- `convertirQuestionBoEnDto()` — Conversion QuestionBO → QuestionDTO
- `trouverQuestionnaireBO()` — Recherche interne d'un QuestionnaireBO par id
- `estMeilleureMeilleure()` — Règle de départage meilleure question (difficulté + grande → + posée → ordre fichier)
- `estMeilleurePire()` — Règle de départage pire question (difficulté + faible → + posée → ordre fichier)
