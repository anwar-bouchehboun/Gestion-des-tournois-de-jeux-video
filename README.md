# Gestion de Tournois E-Sport

## Description du projet

Ce projet est une application de gestion de tournois pour les événements d'e-sport. Elle permet de gérer les joueurs, les équipes et les tournois de jeux vidéo. L'application est construite en utilisant **Java 8**, **Spring Core** pour la gestion de l'inversion de contrôle (IoC) et de l'injection de dépendances (DI), ainsi que **JPA/Hibernate** pour l'accès aux données. L'application utilise **H2** comme base de données embarquée, et offre un menu en console pour interagir avec les différentes fonctionnalités.

## Fonctionnalités principales

- **Gestion des joueurs** : Inscription, modification, suppression et affichage de joueurs.
- **Gestion des équipes** : Création, modification, ajout/retrait de joueurs, affichage d'une ou plusieurs équipes.
- **Gestion des tournois** : Création, modification, ajout/retrait d'équipes, affichage d'un ou plusieurs tournois.
- **Calcul de la durée estimée d'un tournoi** :
  - **Calcul de base** : \( {Durée estimée} = {Nombre d'équipes}{Durée moyenne d'un match}) +{Temps de pause entre les matchs}
  - **Calcul avancé** : \( {Durée estimée} = ({Nombre d'équipes} \times {Durée moyenne d'un match} {Difficulté du jeu}) + {Temps de pause entre les matchs} + {Temps de cérémonie} )

## Structure du projet

Le projet est structuré en plusieurs couches, chacune étant responsable d'une partie spécifique de l'application.

### 1. Couche Modèle (Entités JPA)

- **Joueur** : `pseudo`, `âge`, `équipe`
- **Equipe** : `nom`, `joueurs`, `tournois`, `classement`
- **Tournoi** : `titre`, `jeu`, `date de début`, `date de fin`, `nombre de spectateurs`, `équipes`, `dureeEstimee`, `temps de pause`, `temps de cérémonie`, `statut` (PLANIFIÉ, EN COURS, TERMINÉ, ANNULÉ)
- **Jeu** : `nom`, `difficulté`, `durée moyenne d'un match`

### 2. Couche DAO (Accès aux données via JPA)

- **TournoiDao** : Interface pour le calcul de la durée estimée d'un tournoi.
  - `calculerdureeEstimeeTournoi(Long tournoiId)`
- **Implémentations** :
  - **TournoiDaoImpl** : Calcul basique de la durée estimée.
  - **TournoiDaoExtension** : Extension du DAO pour un calcul avancé (principe Open/Closed).

### 3. Couche Service (Logique métier)

- **TournoiMetier** : Interface pour obtenir la durée estimée d'un tournoi.
  - `obtenirdureeEstimeeTournoi(Long tournoiId)`
- **Implémentation** :
  - **TournoiMetierImpl** : Implémente la logique métier pour la gestion des tournois.

### 4. Couche Utilitaire

- **Gestion des logs** : Utilisation de `LOGGER` pour le suivi et la traçabilité des événements de l'application.

### 5. Console (Menu interactif)

Un menu console permet d'interagir avec l'application pour :

- Gérer les joueurs
- Gérer les équipes
- Gérer les tournois
- Afficher les informations et calculer les durées estimées des tournois.

## Exigences Techniques

- **Base de données** : H2
- **Frameworks** :
  - **Spring Core** : Gestion des beans via `applicationContext.xml`.
  - **JPA/Hibernate** : Accès aux données et validation via des annotations comme `@NotNull`, `@Size`.
- **Gestion des dépendances** : Maven avec fichier `pom.xml`.
- **Tests** : Création de tests unitaires avec **JUnit** et **Mockito**, mesure de la couverture de code avec **JaCoCo**.
- **Patrons de conception** :
  - **Repository Pattern** : Gestion des accès aux données.
  - **Singleton Pattern** : Pour garantir l'unicité des instances là où nécessaire.

## Fonctionnalités Java 8 Utilisées

- **Stream API** et **Lambda Expressions** pour simplifier le traitement des collections.
- **Optional** pour gérer les valeurs potentiellement nulles.
- **Java Time API** pour la manipulation des dates.

## Prérequis

- **Java 8** installé
- **Maven** installé
- Un IDE compatible avec Java (ex. IntelliJ IDEA, Eclipse)

## Installation

1. Clonez le dépôt GitHub :

   ```bash
   git clone https://github.com/anwar-bouchehboun/Gestion-des-tournois-de-jeux-video
   ```

2. Compilez le projet avec Maven :

   ```bash
   mvn clean install
   ```

3. Lancez l'application via la classe principale (`Main.class`).

   ```bash
   java -jar out/spring-app.jar
   ```

## Utilisation

L'application se lance avec un menu en console pour interagir avec les différentes fonctionnalités.

```bash
1. Gérer les équipes
2. Gérer les jeu
3. Gérer les joueurs
4. Gérer les tournois
5. Quitter
```
