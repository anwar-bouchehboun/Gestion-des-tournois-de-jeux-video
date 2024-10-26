package spring.utilis;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PattrenUtils {
    private static Scanner scanner = new Scanner(System.in);


    public static int getIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches("\\d+", input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }
    public static Long getLongInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches("\\d+", input)) {
                return Long.parseLong(input);
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }


    public static String getStringInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;

            } else {
                System.out.println("L'entrée ne peut pas être vide.");
            }
        }
    }


    public static int getStutsType() {
        while (true) {
            System.out.println("Quel type de Status voulez-vous ajouter ? (1.PLANIFIE, 2.EN_COURS,3.TERMINE,4.ANNULE");

            String input = scanner.nextLine().trim();
            if (Pattern.matches("[1234]", input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Type de Status invalide. Veuillez entrer 1 , 2 ,3 ou 4 ");
            }
        }
    }



}
