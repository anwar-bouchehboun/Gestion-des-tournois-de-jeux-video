package spring.view;

import spring.models.Equipe;
import spring.models.Joueur;
import spring.services.JoueurServices;
import spring.services.EquipeServices;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Joueurview {

    private final JoueurServices joueurServices;
    private final EquipeServices equipeServices;
    private final Scanner scanner;

    public Joueurview(JoueurServices joueurServices, EquipeServices equipeServices) {
        this.joueurServices = joueurServices;
        this.equipeServices = equipeServices;
        this.scanner = new Scanner(System.in);
    }

    public void menuJoueur() {
        int choix;
        do {
            System.out.println("\n--- Menu Joueur ---");
            System.out.println("1. Créer un joueur");
            System.out.println("2. Modifier un joueur");
            System.out.println("3. Supprimer un joueur");
            System.out.println("4. Trouver un joueur par ID");
            System.out.println("5. Afficher tous les joueurs");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    creerJoueur();
                    break;
                case 2:
                    modifierJoueur();
                    break;
                case 3:
                    supprimerJoueur();
                    break;
                case 4:
                    trouverJoueurParId();
                    break;
                case 5:
                    afficherTousLesJoueurs();
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private void creerJoueur() {
        System.out.println("--- Création d'un nouveau joueur ---");
        System.out.print("Pseudo : ");
        String pseudo = scanner.nextLine();

        System.out.print("Âge : ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID de l'équipe (0 si pas d'équipe) : ");
        long equipeId = scanner.nextLong();
        scanner.nextLine();

        Joueur nouveauJoueur = new Joueur();
        nouveauJoueur.setPseudo(pseudo);
        nouveauJoueur.setAge(age);

        if (equipeId != 0) {
            Optional<Equipe> equipeOptional = equipeServices.trouverEquipeParId(equipeId);
            if (equipeOptional.isPresent()) {
                nouveauJoueur.setEquipe(equipeOptional.get());
            } else {
                System.out.println("Équipe non trouvée. Le joueur sera créé sans équipe.");
            }
        }

        Joueur joueurCree = joueurServices.creerJoueur(nouveauJoueur);
        System.out.println("Joueur créé avec succès. ID : " + joueurCree.getId());
    }

    private void modifierJoueur() {
        System.out.println("--- Modification d'un joueur ---");
        System.out.print("ID du joueur à modifier : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Joueur> joueurOptional = joueurServices.trouverJoueurParId(id);
        if (joueurOptional.isPresent()) {
            Joueur joueur = joueurOptional.get();
            System.out.println("Joueur trouvé : " + joueur);

            System.out.print("Nouveau pseudo (laisser vide pour ne pas modifier) : ");
            String pseudo = scanner.nextLine();
            if (!pseudo.isEmpty()) {
                joueur.setPseudo(pseudo);
            }

            System.out.print("Nouvel âge (0 pour ne pas modifier) : ");
            int age = scanner.nextInt();
            scanner.nextLine();
            if (age != 0) {
                joueur.setAge(age);
            }

            System.out.print("Nouvel ID d'équipe (0 pour retirer de l'équipe, -1 pour ne pas modifier) : ");
            long equipeId = scanner.nextLong();
            scanner.nextLine();
            if (equipeId == 0) {
                joueur.setEquipe(null);
            } else if (equipeId > 0) {
                Optional<Equipe> equipeOptional = equipeServices.trouverEquipeParId(equipeId);
                if (equipeOptional.isPresent()) {
                    joueur.setEquipe(equipeOptional.get());
                } else {
                    System.out.println("Équipe non trouvée. L'équipe du joueur ne sera pas modifiée.");
                }
            }

            Joueur joueurModifie = joueurServices.modifierJoueur(joueur);
            System.out.println("Joueur modifié avec succès : " + joueurModifie);
        } else {
            System.out.println("Aucun joueur trouvé avec cet ID.");
        }
    }

    private void supprimerJoueur() {
        System.out.println("--- Suppression d'un joueur ---");
        System.out.print("ID du joueur à supprimer : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        joueurServices.supprimerJoueur(id);
        System.out.println("Joueur supprimé avec succès (si existant).");
    }

    private void trouverJoueurParId() {
        System.out.println("--- Recherche d'un joueur par ID ---");
        System.out.print("ID du joueur : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Joueur> joueurOptional = joueurServices.trouverJoueurParId(id);
        if (joueurOptional.isPresent()) {
            System.out.println("Joueur trouvé : " + joueurOptional.get());
        } else {
            System.out.println("Aucun joueur trouvé avec cet ID.");
        }
    }

    private void afficherTousLesJoueurs() {
        System.out.println("--- Liste de tous les joueurs ---");
        List<Joueur> joueurs = joueurServices.trouverTousLesJoueurs();
        if (joueurs.isEmpty()) {
            System.out.println("Aucun joueur trouvé.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-15s%n", "ID", "Pseudo", "Age", "Equipe");
            System.out.println("-------------------------------------------------------------");
            joueurs.forEach(joueur -> System.out.println(joueur.afficherJoueur()));
            System.out.println("Nombre total de joueurs : " + joueurs.size());
        }
    }
}
