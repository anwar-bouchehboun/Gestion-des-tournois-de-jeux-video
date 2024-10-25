package spring.view;

import spring.models.Equipe;
import spring.services.EquipeServices;
import spring.utilis.LoggerMessage;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Equipeview {

    private final EquipeServices equipeServices;
    private final Scanner scanner;

    public Equipeview(EquipeServices equipeServices) {
        this.equipeServices = equipeServices;
        this.scanner = new Scanner(System.in);

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
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

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

    // ... existing code ...

    private void creerEquipe() {
        System.out.println("Création d'une nouvelle équipe");
        System.out.print("Nom de l'équipe : ");
        String nom = scanner.nextLine();
        System.out.print("Classment de l'équipe : ");
        int classment = scanner.nextInt();
        scanner.nextLine();
        Equipe nouvelleEquipe = new Equipe();
        nouvelleEquipe.setNom(nom);
        nouvelleEquipe.setClassement(classment);
        Equipe equipeCreee = equipeServices.creerEquipe(nouvelleEquipe);
        System.out.println("Équipe créée avec l'ID : " + equipeCreee.getId());
    }

    private void modifierEquipe() {
        System.out.print("ID de l'équipe à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
            Equipe equipe = equipeOpt.get();
            System.out.print("Nouveau nom de l'équipe : ");
            String nouveauNom = scanner.nextLine();
            System.out.print("Classment de l'équipe : ");
            int classment = scanner.nextInt();
            equipe.setClassement(classment);
            equipe.setNom(nouveauNom);
            equipeServices.modifierEquipe(equipe);
            System.out.println("Équipe modifiée avec succès.");
        } else {
            System.out.println("Équipe non trouvée.");
        }
    }

    private void supprimerEquipe() {
        System.out.print("ID de l'équipe à supprimer : ");
        Long id = scanner.nextLong();
        equipeServices.supprimerEquipe(id);
        System.out.println("Équipe supprimée (si elle existait).");
    }

    private void trouverEquipeParId() {
        System.out.print("ID de l'équipe à trouver : ");
        Long id = scanner.nextLong();
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
            Equipe equipe = equipeOpt.get();
            System.out.println("Équipe trouvée : " + equipe.getNom());
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
            System.out.print("ID de l'équipe : ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            System.out.print("ID de Joueur : ");
            Long idjoueur = scanner.nextLong();
            scanner.nextLine();

            Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
            if (equipeOpt.isPresent()) {
                Equipe equipe = equipeOpt.get();
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
        System.out.print("ID de l'équipe : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("ID de Joueur : ");
        Long idjoueur = scanner.nextLong();
        scanner.nextLine();
        Optional<Equipe> equipeOpt = equipeServices.trouverEquipeParId(id);
        if (equipeOpt.isPresent()) {
            Equipe equipe = equipeOpt.get();
            System.out.println("Retrait d'un joueur de l'équipe " + equipe.getNom());
            equipeServices.retirerJoueur(idjoueur, equipe);
            System.out.println("Joueur retiré de l'équipe.");
        } else {
            System.out.println("Équipe non trouvée.");
        }
    }
}
