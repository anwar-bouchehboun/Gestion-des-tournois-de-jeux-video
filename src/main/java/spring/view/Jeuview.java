package spring.view;

import spring.services.JeuServices;
import spring.models.Jeu;
import spring.utilis.PattrenUtils;

import java.util.Scanner;
import java.util.Optional;
import java.util.List;

public class Jeuview {

    private JeuServices jeuServices ;
    public Jeuview(JeuServices jeuServices) {
        this.jeuServices = jeuServices;
    }

    public void startJeu() {
    


        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Créer un jeu");
            System.out.println("2. Modifier un jeu");
            System.out.println("3. Trouver un jeu par ID");
            System.out.println("4. Afficher tous les jeux");
            System.out.println("0. Quitter");
           String choix = PattrenUtils.getStringInput("Entrez votre choix: ");

            switch (choix) {
                case "1":
                    creerJeu();
                    break;
                case "2":
                    modifierJeu();
                    break;
                case "3":
                    trouverJeuParId();
                    break;
                case "5":
                    afficherTousLesJeux();
                    break;
                case"0":
                    System.out.println("Au revoir partie Jeu!");
                   return ;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (true);


    }

    private void creerJeu() {
        String nom = PattrenUtils.getStringInput("Entrez le nom du jeu: ");
        String difficulte = PattrenUtils.getStringInput("Entrez la difficulté du jeu: ");
        int dureeMoyenne = PattrenUtils.getIntInput("Entrez la durée moyenne du jeu (en minutes): ");;

        Jeu jeu = new Jeu();
        jeu.setNom(nom);
        jeu.setDifficulte(difficulte);
        jeu.setDureeMoyenne(dureeMoyenne);

        jeuServices.creerJeu(jeu);
        System.out.println("Jeu créé avec succès.");
    }

    private void modifierJeu() {
        Long id = PattrenUtils.getLongInput("Entrez l'ID du jeu à modifier:");
        Optional<Jeu> jeux = jeuServices.trouverJeuParId(id);
        if (jeux.isPresent()) {
            System.out.println("Jeu trouvé: " + jeux.get());
            String nom = PattrenUtils.getStringInput("Entrez le nouveau nom du jeu: ");
            String difficulte = PattrenUtils.getStringInput("Entrez la nouvelle difficulté du jeu: ");
            int dureeMoyenne = PattrenUtils.getIntInput("Entrez la nouvelle durée moyenne du jeu (en minutes): ");;


            Jeu jeu = new Jeu();
            jeu.setId(id);
            jeu.setNom(nom);
            jeu.setDifficulte(difficulte);
            jeu.setDureeMoyenne(dureeMoyenne);

            jeuServices.modifierJeu(jeu);
            System.out.println("Jeu modifié avec succès.");
        } else {
            System.out.println("Jeu non trouvé.");
        }

    }



    private void trouverJeuParId() {
        Long id = PattrenUtils.getLongInput("Entrez l'ID du jeu à trouver: ");
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
