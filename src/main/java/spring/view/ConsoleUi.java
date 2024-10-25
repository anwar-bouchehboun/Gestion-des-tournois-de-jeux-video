package spring.view;

import spring.models.Equipe;
import spring.services.EquipeServices;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUi {
    private final Equipeview equipeview;
    private final Jeuview jeuview;
    private  final Joueurview joueurview;
    private final Tournoiview tournoiview;

    private final Scanner scanner;


    public ConsoleUi( Equipeview equipeview,Jeuview jeuview,Joueurview joueurview,Tournoiview tournoiview ) {
        this.equipeview = equipeview;
        this.jeuview=jeuview;
        this.joueurview=joueurview;      
        this.tournoiview=tournoiview;
        this.scanner = new Scanner(System.in);

    }


    public void start(){
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Menu équipe");
            System.out.println("2. Menu  Jeu");
            System.out.println("3. Menu  Joueur");
            System.out.println("4. Menu  Tournoi");

            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            String choix = scanner.nextLine();

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
               /*    case 5:

                    break;
                case 6:

                    break;
                case 7:
                    break;*/
                case "0":
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }





}
