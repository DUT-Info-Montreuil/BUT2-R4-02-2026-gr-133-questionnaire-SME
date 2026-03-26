# Prompt pour Claude Code — Projet SME Questionnaire

## Instructions

Tu es un développeur Java senior qui travaille sur le projet **SME Questionnaire** d'un jeu de quizz.

### Étape 1 : Analyse du code existant

Commence par analyser tout le code existant dans le projet :

```
find . -name "*.java" -o -name "pom.xml" -o -name "*.csv" | head -50
cat pom.xml
find src -name "*.java" -exec echo "=== {} ===" \; -exec cat {} \;
```

Identifie :
- Ce qui existe déjà (classes, interfaces, implémentations)
- Ce qui manque par rapport au CLAUDE.md
- Ce qui est mal fait ou incomplet

### Étape 2 : Lire le contexte complet

Lis le fichier `CLAUDE.md` à la racine du projet. Il contient TOUT le contexte : objets métier (BO/DTO), services, exceptions, diagrammes UML, règles métier, conventions.

### Étape 3 : Plan de branches feature

Le prof impose la nomenclature `feature/devNOM`. Voici les branches à créer dans l'ordre logique de développement :

```
feature/devEntities          → QuestionBO, QuestionnaireBO, QuestionDTO, QuestionnaireDTO, StatistiqueQuestionnaireDTO
feature/devExceptions        → Toutes les exceptions métier (6 exceptions)
feature/devChargerFichier    → Service chargerFichier + ResourceLoader CSV + tests unitaires
feature/devFournirListe      → Service fournirListeQuestionnaires + tests unitaires
feature/devFournirQuest      → Service fournirUnQuestionnaire + tests unitaires
feature/devMajStats          → Service majStatQuestions + tests unitaires
feature/devFournirStats      → Service fournirStatsQuestions (avec règles départage) + tests unitaires
feature/devTestsIntegration  → Tests d'intégration globaux (charger + fournir + jouer + stats)
```

### Étape 4 : Développement

Pour chaque branche, respecte ces règles :
1. **Créer la branche** depuis main/develop
2. **Coder** la fonctionnalité correspondante
3. **Écrire les tests unitaires** JUnit 5 pour chaque méthode
4. **Vérifier** que `mvn test` passe
5. **Committer** avec un message clair en français

### Règles de code OBLIGATOIRES

- **Packages** : respecter exactement la structure `fr.iut.montreuil.R4_S02_2023.prof.questionnaire_sme.*` (adapter l'année si nécessaire selon ce qui existe déjà)
- **BO vs DTO** : les BO restent internes au SME, jamais exposés. Seuls les DTO sortent vers le SINT.
- **Conversion BO↔DTO** : créer des méthodes de conversion dans une classe utilitaire ou dans les implémentations
- **Stockage en mémoire** : utiliser des `List<QuestionnaireBO>` en attribut de la classe d'implémentation (pas de BDD)
- **CSV** : le séparateur est probablement une tabulation, vérifier le fichier. Pas de ligne d'en-tête.
- **Tests** : JUnit 5 + Mockito, couvrir les cas nominaux ET les cas d'erreur

### Ce qu'il NE FAUT PAS faire

- NE PAS coder les joueurs (c'est le binôme joueur)
- NE PAS coder l'interface console (c'est le binôme SINT/SAPP)
- NE PAS coder les services d'intégration (c'est le binôme SINT)
- NE PAS utiliser de base de données, tout est en mémoire
- NE PAS modifier la structure des packages imposée par le DAT
- NE PAS FAIRE DES PUSH SUR GIT TOTALEMENT INTERDIT

---

Commence par l'étape 1 (analyse du code existant) puis propose-moi le plan détaillé avant de coder.