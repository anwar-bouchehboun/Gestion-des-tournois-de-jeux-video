package spring.view;

import spring.models.Equipe;
import spring.services.EquipeServices;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUi {
    private final Equipeview equipeview;
    public final Jeuview jeuview;
    private final Scanner scanner;


    public ConsoleUi( Equipeview equipeview,Jeuview jeuview ) {
        this.equipeview = equipeview;
        this.jeuview=jeuview;
        this.scanner = new Scanner(System.in);

    }


    public void start(){
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Menu équipe");
            System.out.println("2. Menu  Jeu");

            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    equipeview.StartEquipe();
                    break;
                case 2:
                    jeuview.startJeu();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }





}
