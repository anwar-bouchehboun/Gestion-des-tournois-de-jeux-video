package spring.utilis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static LocalDate getDateInput(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(input, formatter);
                    if (date.isAfter(LocalDate.now())) {
                        return date;
                    } else {
                        LoggerMessage.error("La date doit être après la date actuelle.");
                    }
                } catch (DateTimeParseException e) {
                    LoggerMessage.error("Format de date invalide. Veuillez entrer la date au format 'yyyy-MM-dd'.");
                }
            } else {
                LoggerMessage.error("L'entrée ne peut pas être vide.");
            }
        }
    }
}
