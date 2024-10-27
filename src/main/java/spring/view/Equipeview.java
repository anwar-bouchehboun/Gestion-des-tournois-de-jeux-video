package spring.view;

import spring.models.Equipe;
import spring.services.EquipeServices;
import spring.utilis.LoggerMessage;
import spring.utilis.PattrenUtils;

import java.util.List;
import java.util.Optional;


public class Equipeview {

    private final EquipeServices equipeServices;
    private Equipe equipe;


    public Equipeview(EquipeServices equipeServices, Equipe equipe) {
        this.equipeServices = equipeServices;
        this.equipe=equipe;
    }

    public void StartEquipe() {
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Créer une équipe");
            System.out.println("2. Modifier une équipe");
            System.out.println("3. Supprimer une équipe");
            System.out.println("4. Trouver une équipe par ID");
            System.out.println("5. Afficher toutes les équipes");
            System.out.println("6. Ajouter un joueur à une équipe");
            System.out.println("7. Retirer un joueur d'une équipe");
            System.out.println("0. Quitter");


            int choix = PattrenUtils.getIntInput("Choisissez une option : ");


            switch (choix) {
                case 1:
                    creerEquipe();
                    break;
                case 2:
                    modifierEquipe();
                    break;
                case 3:
                    supprimerEquipe();
                    break;
                case 4:
                    trouverEquipeParId();
                    break;
                case 5:
                    afficherToutesLesEquipes();
                    break;
                case 6:
                    ajouterJoueur();
                    break;
                case 7:
                    retirerJoueur();
                    break;
                case 0:
                    System.out.println("Au revoir partie Equipe !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    private void creerEquipe() {

        System.out.println("Création d'une nouvelle équipe");
        String nom = PattrenUtils.getStringInput("Nom de l'équipe : ");
        int classment = PattrenUtils.getIntInput("Classment de l'équipe : ");
        equipe.setNom(nom);
        equipe.setClassement(classment);
        Equipe equipeCreee = equipeServices.creerEquipe(equipe);
        System.out.println("Équipe créée avec l'ID : " + equipeCreee.getId());
    }

    private void modifierEquipe() {
        System.out.println("Modifier  équipe");
        Long id = PattrenUtils.getLongInput("ID de l'équipe à modifier :");
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
            equipe = equipeOpt.get();
            String nouveauNom = PattrenUtils.getStringInput("Nouveau nom  de l'équipe : ");
            int classment = PattrenUtils.getIntInput("Classment de l'équipe : ");
            equipe.setClassement(classment);
            equipe.setNom(nouveauNom);
            equipeServices.modifierEquipe(equipe);
            System.out.println("Équipe modifiée avec succès.");
        } else {
            System.out.println("Équipe non trouvée.");
        }
    }

    private void supprimerEquipe() {
        Long id = PattrenUtils.getLongInput("ID de l'équipe à supprimer  :");;
        equipeServices.supprimerEquipe(id);
        System.out.println("Équipe supprimée (si elle existait).");
    }

    private void trouverEquipeParId() {
        Long id = PattrenUtils.getLongInput("ID de l'équipe à trouver :");
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
            equipe = equipeOpt.get();
            System.out.printf("Équipe trouvée : " + equipe.getNom(),"Classment "+ equipe.getClassement());
        } else {
            System.out.println("Équipe non trouvée.");
        }
    }

    private void afficherToutesLesEquipes() {
        List<Equipe> equipes = equipeServices.trouverToutesLesEquipes();
        if (equipes.isEmpty()) {
            System.out.println("Aucune équipe trouvée.");
        } else {
            System.out.printf("%-5s %-20s %-10s%n", "ID", "Nom", "Classement");
            System.out.println("----------------------------------------");
            equipes.stream().forEach(equipe -> System.out.printf("%-5d %-20s %-10d%n",
                    equipe.getId(), equipe.getNom(), equipe.getClassement()));
        }
    }

    private void ajouterJoueur() {
        try {
            Long id = PattrenUtils.getLongInput("ID de l'équipe à Ajouter  :");;
            Long idjoueur = PattrenUtils.getLongInput("ID de Joueur à Ajouter  :");
            Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
            if (equipeOpt.isPresent()) {
                equipe = equipeOpt.get();
                System.out.println("Ajout d'un joueur à l'équipe " + equipe.getNom());
                equipeServices.ajouterJoueur(idjoueur, equipe);
                System.out.println("Joueur ajouté avec succès.");
            } else {
                System.out.println("Équipe non trouvée. Veuillez vérifier l'ID et réessayer.");
            }
        } catch (Exception e) {
            LoggerMessage.error("Une erreur s'est produite lors de l'ajout du joueur : " + e.getMessage());

        }
    }

    private void retirerJoueur() {

        Long id = PattrenUtils.getLongInput("ID de l'équipe   :");;
        Long idjoueur = PattrenUtils.getLongInput("ID de Joueur à Retier  :");
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
          equipe = equipeOpt.get();
            System.out.println("Retrait d'un joueur de l'équipe " + equipe.getNom());
            equipeServices.retirerJoueur(idjoueur, equipe);
            System.out.println("Joueur retiré de l'équipe.");
        } else {
            System.out.println("Équipe non trouvée.");
        }
    }
}
