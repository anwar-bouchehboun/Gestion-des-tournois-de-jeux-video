package spring.view;

import spring.models.Equipe;
import spring.services.EquipeServices;
import spring.utilis.PattrenUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUi {
    private final Equipeview equipeview;
    private final Jeuview jeuview;
    private  final Joueurview joueurview;
    private final Tournoiview tournoiview;



    public ConsoleUi( Equipeview equipeview,Jeuview jeuview,Joueurview joueurview,Tournoiview tournoiview ) {
        this.equipeview = equipeview;
        this.jeuview=jeuview;
        this.joueurview=joueurview;      
        this.tournoiview=tournoiview;

    }


    public void start(){
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1.  Gérer les équipes");
            System.out.println("2. Gérer les Jeu");
            System.out.println("3.Gérer les joueurs");
            System.out.println("4. Gérer les tournois");
            System.out.println("0. Quitter");

            String choix = PattrenUtils.getStringInput("Choisissez une option :");

            switch (choix) {
                case "1":
                    equipeview.StartEquipe();
                    break;
                case "2":
                    jeuview.startJeu();
                    break;
                case "3":
                    joueurview.menuJoueur();
                    break;
             case "4":
             tournoiview.afficherMenu();
                    break;
                case "0":
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }





}
