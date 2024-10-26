package spring.view;

import spring.models.Tournoi;
import spring.models.Equipe;
import spring.models.Jeu;
import spring.services.TournoiServices;
import spring.services.EquipeServices;
import spring.services.JeuServices;
import spring.utilis.DateUtils;
import spring.utilis.PattrenUtils;

import java.util.List;
import java.time.LocalDate;

public class Tournoiview {
    private final TournoiServices tournoiServices;
    private  final  EquipeServices equipeServices;
    private final JeuServices jeuServices;

    public Tournoiview(TournoiServices tournoiServices, EquipeServices equipeServices, JeuServices jeuServices) {
        this.tournoiServices = tournoiServices;
        this.jeuServices = jeuServices;
        this.equipeServices=equipeServices;
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

            int choix = PattrenUtils.getIntInput(" Entre Choix : ");


            switch (choix) {
                case 1: creerTournoi(); break;
                case 2: modifierTournoi(); break;
                case 3: afficherTousTournois(); break;
                case 4: ajouterEquipeAuTournoi(); break;
                case 5: retirerEquipeDuTournoi(); break;
                case 6: afficherEquipesDuTournoi(); break;
                case 7: obtenirDureeEstimeeTournoi(); break;
                case 0:
                    System.out.println("return Menu Prncipal ");
                    return;
                default: System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void creerTournoi() {
        System.out.println("Création d'un nouveau tournoi");
        String titre = PattrenUtils.getStringInput("Titre du tournoi : ");
        
        Long jeuId = PattrenUtils.getLongInput("ID du jeu :");

        Jeu jeu = jeuServices.trouverJeuParId(jeuId).orElse(null);
        if (jeu == null) {
            System.out.println("Jeu non trouvé. Création du tournoi annulée.");
            return;
        }

        LocalDate dateDebut = DateUtils.getDateInput("Date de début (dd/MM/yyyy) : ");
        LocalDate dateFin = DateUtils.getDateInput("Date de fin (dd/MM/yyyy) :");


        int nombreSpectateurs = PattrenUtils.getIntInput("Nombre de spectateurs :");
        int dureeEstimee =PattrenUtils.getIntInput("Durée estimée (en minutes) :");
        int tempsPause = PattrenUtils.getIntInput("Temps de pause (en minutes) :");
        int tempsCeremonie = PattrenUtils.getIntInput("Temps de cérémonie (en minutes) : ");



        String statut = PattrenUtils.getStringInput("Quel type de Status voulez-vous ajouter ? (1.PLANIFIE, 2.EN_COURS,3.TERMINE,4.ANNULE");

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

        Long id = PattrenUtils.getLongInput("ID du tournoi à modifier :");
        Tournoi tournoi = tournoiServices.trouverTournoiParId(id).orElse(null);
        if (tournoi == null) {
            System.out.println("Tournoi non trouvé.");
            return;
        }


        String titre = PattrenUtils.getStringInput("Nouveau titre (laisser vide pour ne pas changer) :");
        if (!titre.isEmpty()) {
            tournoi.setTitre(titre);
        }

        Long jeuId = PattrenUtils.getLongInput("Nouvel ID du jeu (0 pour ne pas changer) :");


        if (jeuId != 0) {
            Jeu jeu = jeuServices.trouverJeuParId(jeuId).orElse(null);
            if (jeu != null) {
                tournoi.setJeu(jeu);
            } else {
                System.out.println("Jeu non trouvé. Le jeu n'a pas été modifié.");
            }
        }


        LocalDate dateDebutStr = DateUtils.getDateInput("Nouvelle date de début ((dd/MM/yyyy), laisser vide pour ne pas changer) : ");


            tournoi.setDateDebut(dateDebutStr);


        LocalDate dateFinStr = DateUtils.getDateInput("\"Nouvelle date de fin ((dd/MM/yyyy), laisser vide pour ne pas changer) :");
            tournoi.setDateFin(dateFinStr);



        int nombreSpectateurs = PattrenUtils.getIntInput("Nouveau nombre de spectateurs (0 pour ne pas changer) :");
        if (nombreSpectateurs != 0) {
            tournoi.setNombreSpectateurs(nombreSpectateurs);
        }

        int dureeEstimee =PattrenUtils.getIntInput("Nouvelle durée estimée (0 pour ne pas changer) :");
        if (dureeEstimee != 0) {
            tournoi.setDureeEstimee(dureeEstimee);
        }


        int tempsPause = PattrenUtils.getIntInput("Nouveau temps de pause (0 pour ne pas changer) : ");
        if (tempsPause != 0) {
            tournoi.setTempsPause(tempsPause);
        }


        int tempsCeremonie = PattrenUtils.getIntInput("Nouveau temps de cérémonie (0 pour ne pas changer) :");
        if (tempsCeremonie != 0) {
            tournoi.setTempsCeremonie(tempsCeremonie);
        }


        System.out.print("Nouveau statut (laisser vide pour ne pas changer) : ");
        String statut = PattrenUtils.getStringInput("Nouveau statut (laisser vide pour ne pas changer) : (1.PLANIFIE, 2.EN_COURS,3.TERMINE,4.ANNULE");
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

        Long tournoiId = PattrenUtils.getLongInput("ID du tournoi :");

        Long equipeId = PattrenUtils.getLongInput("ID de l'équipe à ajouter : ");


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
        Long tournoiId = PattrenUtils.getLongInput("ID du tournoi :");

        Long equipeId = PattrenUtils.getLongInput("ID de l'équipe à Retier : ");

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
        Long tournoiId = PattrenUtils.getLongInput("ID du tournoi :");

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
        Long tournoiId = PattrenUtils.getLongInput("ID du tournoi :");

        Tournoi tournoi = tournoiServices.trouverTournoiParId(tournoiId).orElse(null);
        if (tournoi == null) {
            System.out.println("Tournoi non trouvé.");
            return;
        }
        int dureeEstimee = tournoiServices.obtenirDureeEstimeeTournoi(tournoiId);
        System.out.println("Durée estimée du tournoi : " + dureeEstimee + " jours");
    }


}
