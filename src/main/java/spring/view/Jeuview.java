package spring.view;

import spring.services.JeuServices;
import spring.models.Jeu;
import java.util.Scanner;
import java.util.Optional;
import java.util.List;

public class Jeuview {

    private JeuServices jeuServices ;
    private Scanner scanner;
    public Jeuview(JeuServices jeuServices) {
        this.jeuServices = jeuServices;
        this.scanner = new Scanner(System.in);

    }

    public void startJeu() {
    
        int choix;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Créer un jeu");
            System.out.println("2. Modifier un jeu");
            System.out.println("3. Supprimer un jeu");
            System.out.println("4. Trouver un jeu par ID");
            System.out.println("5. Afficher tous les jeux");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choix) {
                case 1:
                    creerJeu();
                    break;
                case 2:
                    modifierJeu();
                    break;
                case 3:
                    supprimerJeu();
                    break;
                case 4:
                    trouverJeuParId();
                    break;
                case 5:
                    afficherTousLesJeux();
                    break;
                case 0:
                    System.out.println("Au revoir partie Jeu!");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 0);

        scanner.close();
    }

    private void creerJeu() {
     
        System.out.print("Entrez le nom du jeu: ");
        String nom = scanner.nextLine();
        System.out.print("Entrez la difficulté du jeu: ");
        String difficulte = scanner.nextLine();
        System.out.print("Entrez la durée moyenne du jeu (en minutes): ");
        int dureeMoyenne = scanner.nextInt();

        Jeu jeu = new Jeu();
        jeu.setNom(nom);
        jeu.setDifficulte(difficulte);
        jeu.setDureeMoyenne(dureeMoyenne);

        jeuServices.creerJeu(jeu);
        System.out.println("Jeu créé avec succès.");
    }

    private void modifierJeu() {
        System.out.print("Entrez l'ID du jeu à modifier: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        System.out.print("Entrez le nouveau nom du jeu: ");
        String nom = scanner.nextLine();
        System.out.print("Entrez la nouvelle difficulté du jeu: ");
        String difficulte = scanner.nextLine();
        System.out.print("Entrez la nouvelle durée moyenne du jeu (en minutes): ");
        int dureeMoyenne = scanner.nextInt();

        Jeu jeu = new Jeu();
        jeu.setId(id);
        jeu.setNom(nom);
        jeu.setDifficulte(difficulte);
        jeu.setDureeMoyenne(dureeMoyenne);

        jeuServices.modifierJeu(jeu);
        System.out.println("Jeu modifié avec succès.");
    }

    private void supprimerJeu() {
        System.out.print("Entrez l'ID du jeu à supprimer: ");
        Long id = scanner.nextLong();
        jeuServices.supprimerJeu(id);
        System.out.println("Jeu supprimé avec succès.");
    }

    private void trouverJeuParId() {
        System.out.print("Entrez l'ID du jeu à trouver: ");
        Long id = scanner.nextLong();
        Optional<Jeu> jeu = jeuServices.trouverJeuParId(id);
        if (jeu.isPresent()) {
            System.out.println("Jeu trouvé: " + jeu.get());
        } else {
            System.out.println("Jeu non trouvé.");
        }
    }

    private void afficherTousLesJeux() {
        List<Jeu> jeux = jeuServices.trouverTousLesJeux();
        if (jeux.isEmpty()) {
            System.out.println("Aucun jeu trouvé.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-15s%n", "ID", "Nom", "Difficulté", "Durée Moyenne");
            System.out.println("-------------------------------------------------------------");
            jeux.stream().forEach(jeu -> 
                System.out.printf("%-5d %-20s %-15s %-15d%n", 
                    jeu.getId(), jeu.getNom(), jeu.getDifficulte(), jeu.getDureeMoyenne())
            );
        }
    }
}
