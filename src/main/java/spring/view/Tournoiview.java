package spring.view;

import spring.interfaces.impl.TournoiDaoImpl;
import spring.models.Tournoi;
import spring.models.Equipe;
import spring.models.Jeu;
import spring.services.TournoiServices;
import spring.services.EquipeServices;
import spring.services.JeuServices;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tournoiview {
    private final TournoiServices tournoiServices;
    private  final  EquipeServices equipeServices;
    private final JeuServices jeuServices;
    private final Scanner scanner;

    public Tournoiview(TournoiServices tournoiServices, EquipeServices equipeServices, JeuServices jeuServices) {
        this.tournoiServices = tournoiServices;
        this.jeuServices = jeuServices;
        this.equipeServices=equipeServices;
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenu() {
        while (true) {
            System.out.println("\n--- Menu Tournoi ---");
            System.out.println("1. Créer un tournoi");
            System.out.println("2. Modifier un tournoi");

            System.out.println("3. Afficher tous les tournois");
            System.out.println("4. Ajouter une équipe à un tournoi");
            System.out.println("5. Retirer une quipe d'un tournoi");
            System.out.println("6. Afficher les équipes d'un tournoi");
            System.out.println("7. Obtenir la durée estimée d'un tournoi");
            System.out.println("0. Créer principal");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choix) {
                case 1: creerTournoi(); break;
                case 2: modifierTournoi(); break;
                case 3: afficherTousTournois(); break;
                case 4: ajouterEquipeAuTournoi(); break;
                case 5: retirerEquipeDuTournoi(); break;
                case 6: afficherEquipesDuTournoi(); break;
                case 7: obtenirDureeEstimeeTournoi(); break;
                case 0: return;
                default: System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void creerTournoi() {
        System.out.println("Création d'un nouveau tournoi");
        System.out.print("Titre du tournoi : ");
        String titre = scanner.nextLine();
        
        System.out.print("ID du jeu : ");
        Long jeuId = scanner.nextLong();
        scanner.nextLine(); 
        Jeu jeu = jeuServices.trouverJeuParId(jeuId).orElse(null);
        if (jeu == null) {
            System.out.println("Jeu non trouvé. Création du tournoi annulée.");
            return;
        }

        System.out.print("Date de début (YYYY-MM-DD) : ");
        LocalDate dateDebut = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.print("Date de fin (YYYY-MM-DD) : ");
        LocalDate dateFin = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Nombre de spectateurs : ");
        int nombreSpectateurs = scanner.nextInt();
        System.out.print("Durée estimée (en minutes) : ");
        int dureeEstimee = scanner.nextInt();
        System.out.print("Temps de pause (en minutes) : ");
        int tempsPause = scanner.nextInt();
        System.out.print("Temps de cérémonie (en minutes) : ");
        int tempsCeremonie = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Statut : ");
        String statut = scanner.nextLine();

        Tournoi nouveauTournoi = new Tournoi();
        nouveauTournoi.setTitre(titre);
        nouveauTournoi.setJeu(jeu);
        nouveauTournoi.setDateDebut(dateDebut);
        nouveauTournoi.setDateFin(dateFin);
        nouveauTournoi.setNombreSpectateurs(nombreSpectateurs);
        nouveauTournoi.setDureeEstimee(dureeEstimee);
        nouveauTournoi.setTempsPause(tempsPause);
        nouveauTournoi.setTempsCeremonie(tempsCeremonie);
        nouveauTournoi.setStatut(statut);

        Tournoi tournoiCree = tournoiServices.creerTournoi(nouveauTournoi);
        System.out.println("Tournoi créé avec l'ID : " + tournoiCree.getId());
    }

    private void modifierTournoi() {
        System.out.print("ID du tournoi à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Tournoi tournoi = tournoiServices.trouverTournoiParId(id).orElse(null);
        if (tournoi == null) {
            System.out.println("Tournoi non trouvé.");
            return;
        }

        System.out.print("Nouveau titre (laisser vide pour ne pas changer) : ");
        String titre = scanner.nextLine();
        if (!titre.isEmpty()) {
            tournoi.setTitre(titre);
        }

        System.out.print("Nouvel ID du jeu (0 pour ne pas changer) : ");
        Long jeuId = scanner.nextLong();
        scanner.nextLine();
        if (jeuId != 0) {
            Jeu jeu = jeuServices.trouverJeuParId(jeuId).orElse(null);
            if (jeu != null) {
                tournoi.setJeu(jeu);
            } else {
                System.out.println("Jeu non trouvé. Le jeu n'a pas été modifié.");
            }
        }

        System.out.print("Nouvelle date de début (YYYY-MM-DD, laisser vide pour ne pas changer) : ");
        String dateDebutStr = scanner.nextLine();
        if (!dateDebutStr.isEmpty()) {
            tournoi.setDateDebut(LocalDate.parse(dateDebutStr, DateTimeFormatter.ISO_LOCAL_DATE));
        }

        System.out.print("Nouvelle date de fin (YYYY-MM-DD, laisser vide pour ne pas changer) : ");
        String dateFinStr = scanner.nextLine();
        if (!dateFinStr.isEmpty()) {
            tournoi.setDateFin(LocalDate.parse(dateFinStr, DateTimeFormatter.ISO_LOCAL_DATE));
        }

        System.out.print("Nouveau nombre de spectateurs (0 pour ne pas changer) : ");
        int nombreSpectateurs = scanner.nextInt();
        if (nombreSpectateurs != 0) {
            tournoi.setNombreSpectateurs(nombreSpectateurs);
        }

        System.out.print("Nouvelle durée estimée (0 pour ne pas changer) : ");
        int dureeEstimee = scanner.nextInt();
        if (dureeEstimee != 0) {
            tournoi.setDureeEstimee(dureeEstimee);
        }

        System.out.print("Nouveau temps de pause (0 pour ne pas changer) : ");
        int tempsPause = scanner.nextInt();
        if (tempsPause != 0) {
            tournoi.setTempsPause(tempsPause);
        }

        System.out.print("Nouveau temps de cérémonie (0 pour ne pas changer) : ");
        int tempsCeremonie = scanner.nextInt();
        if (tempsCeremonie != 0) {
            tournoi.setTempsCeremonie(tempsCeremonie);
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Nouveau statut (laisser vide pour ne pas changer) : ");
        String statut = scanner.nextLine();
        if (!statut.isEmpty()) {
            tournoi.setStatut(statut);
        }

        Tournoi tournoiModifie = tournoiServices.modifierTournoi(tournoi);
        System.out.println("Tournoi modifié avec succès : " + tournoiModifie.getId());
    }



    private void afficherTousTournois() {
        List<Tournoi> tournois = tournoiServices.trouverTousTournois();
        if (tournois.isEmpty()) {
            System.out.println("Aucun tournoi trouvé.");
        } else {
            System.out.println("+---------+-----------------+------------+------------+------------+-------------------+---------------+---------------+------------------+----------+----------------+");
            System.out.printf("| %-7s | %-15s | %-10s | %-10s | %-10s | %-17s | %-13s | %-13s | %-16s | %-10s | %-14s |%n",
                    "ID", "Titre", "Jeu", "Début", "Fin", "Spectateurs", "Durée (min)", "Pause (min)", "Cérémonie (min)", "Statut", "Nb. d'équipes");
            System.out.println("+---------+-----------------+------------+------------+------------+-------------------+---------------+---------------+------------------+----------+----------------+");
            
            tournois.stream().forEach(tournoi -> {
                System.out.printf("| %-7d | %-15s | %-10s | %-10s | %-10s | %-17d | %-13d | %-13d | %-16d | %-8s | %-14d |%n",
                    tournoi.getId(),
                   tournoi.getTitre(),
                  tournoi.getJeu() != null ? tournoi.getJeu().getNom() : "N/A",
                    tournoi.getDateDebut(),
                    tournoi.getDateFin(),
                    tournoi.getNombreSpectateurs(),
                    tournoi.getDureeEstimee(),
                    tournoi.getTempsPause(),
                    tournoi.getTempsCeremonie(),
                   tournoi.getStatut(),
                    tournoi.getEquipes() != null ? tournoi.getEquipes().size() : 0
                );
            });
            System.out.println("+---------+-----------------+------------+------------+------------+-------------------+---------------+---------------+------------------+----------+----------------+");
        }
    
    }

    private void ajouterEquipeAuTournoi() {
        System.out.print("ID du tournoi : ");
        Long tournoiId = scanner.nextLong();
        System.out.print("ID de l'équipe à ajouter : ");
        Long equipeId = scanner.nextLong();
        scanner.nextLine();

        Tournoi tournoi = tournoiServices.trouverTournoiParId(tournoiId).orElse(null);
        Equipe equipe=equipeServices.trouverEquipeParId(equipeId).orElse(null);
        if (tournoi == null || equipe==null) {
            System.out.println("Tournoi non trouvé. ou Equipe non Trouvé");
            return;
        }

        tournoiServices.ajouterEquipeAuTournoiParIds(tournoi.getId(),equipeId);
        System.out.println("Équipe ajoutée au tournoi avec succès.");
    }

    private void retirerEquipeDuTournoi() {
        System.out.print("ID du tournoi : ");
        Long tournoiId = scanner.nextLong();
        System.out.print("ID de l'équipe à retirer : ");
        Long equipeId = scanner.nextLong();
        scanner.nextLine();
        Tournoi tournoi = tournoiServices.trouverTournoiParId(tournoiId).orElse(null);
        Equipe equipe=equipeServices.trouverEquipeParId(equipeId).orElse(null);
        if (tournoi == null || equipe==null) {
            System.out.println("Tournoi non trouvé ou Equipe non Trouve");
            return;
        }
        tournoiServices.retirerEquipeDuTournoi(tournoi.getId(),equipeId);
        System.out.println("Équipe retirée du tournoi avec succès.");
    }

    private void afficherEquipesDuTournoi() {
        System.out.print("ID du tournoi : ");
        Long tournoiId = scanner.nextLong();
        scanner.nextLine();

        Tournoi tournoi = tournoiServices.trouverTournoiParId(tournoiId).orElse(null);
        if (tournoi == null) {
            System.out.println("Tournoi non trouvé.");
            return;
        }

        List<Equipe> equipes = tournoi.getEquipes();
        if (equipes.isEmpty()) {
            System.out.println("Aucune équipe dans ce tournoi.");
        } else {
            System.out.printf("%-5s %-20s %-10s%n", "ID", "Nom", "Classement");
            System.out.println("----------------------------------------");
            equipes.stream().forEach(equipe -> System.out.printf("%-5d %-20s %-10d%n",
                    equipe.getId(), equipe.getNom(), equipe.getClassement()));
        }
    }

    private void obtenirDureeEstimeeTournoi() {
        System.out.print("ID du tournoi : ");
        Long tournoiId = scanner.nextLong();
        scanner.nextLine();
        Tournoi tournoi = tournoiServices.trouverTournoiParId(tournoiId).orElse(null);
        if (tournoi == null) {
            System.out.println("Tournoi non trouvé.");
            return;
        }
        int dureeEstimee = tournoiServices.obtenirDureeEstimeeTournoi(tournoiId);
        System.out.println("Durée estimée du tournoi : " + dureeEstimee + " jours");
    }


}
