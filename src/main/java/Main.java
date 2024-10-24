
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.view.ConsoleUi;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsoleUi consoleUI = (ConsoleUi) context.getBean("ConsoleUi");
        consoleUI.start();
        /*
         * Server h2WebServer = context.getBean("h2WebServer", Server.class);
         * 
         * h2WebServer.start();
         * String h2ConsoleUrl = h2WebServer.getURL();
         * 
         * logger.info("H2 Console URL: {}", h2ConsoleUrl);
         * System.out.println(h2ConsoleUrl);
         */

        /*
         * EquipeImp equipeImp = new EquipeImp();
         * Scanner scanner = new Scanner(System.in);
         * int choice;
         * 
         * do {
         * System.out.println("\n--- Menu ---");
         * System.out.println("1. Créer une équipe");
         * System.out.println("2. Modifier une équipe");
         * System.out.println("3. Supprimer une équipe");
         * System.out.println("4. Afficher toutes les équipes");
         * System.out.println("5. Quitter");
         * System.out.print("Choisissez une option: ");
         * choice = scanner.nextInt();
         * scanner.nextLine(); // Consommer la nouvelle ligne
         * 
         * switch (choice) {
         * case 1:
         * // Créer une équipe
         * System.out.print("Entrez le nom de l'équipe: ");
         * String nom = scanner.nextLine();
         * Equipe equipe = new Equipe();
         * equipe.setNom(nom);
         * equipe.setClassement(1); // Exemple de classement
         * equipeImp.creer(equipe);
         * break;
         * 
         * case 2:
         * // Modifier une équipe
         * System.out.print("Entrez l'ID de l'équipe à modifier: ");
         * Long idModifier = scanner.nextLong();
         * scanner.nextLine(); // Consommer la nouvelle ligne
         * Optional<Equipe> equipeAModifier = equipeImp.trouverParId(idModifier);
         * if (equipeAModifier.isPresent()) {
         * System.out.print("Entrez le nouveau nom de l'équipe: ");
         * String nouveauNom = scanner.nextLine();
         * Equipe equipeModifiee = equipeAModifier.get();
         * equipeModifiee.setNom(nouveauNom);
         * equipeImp.modifier(equipeModifiee);
         * } else {
         * System.out.println("Équipe non trouvée.");
         * }
         * break;
         * 
         * case 3:
         * // Supprimer une équipe
         * System.out.print("Entrez l'ID de l'équipe à supprimer: ");
         * Long idSupprimer = scanner.nextLong();
         * equipeImp.supprimer(idSupprimer);
         * break;
         * 
         * case 4:
         * // Afficher toutes les équipes
         * List<Equipe> equipes = equipeImp.trouverTous();
         * System.out.println("Liste des équipes :");
         * for (Equipe e : equipes) {
         * System.out.println(e);
         * }
         * break;
         * 
         * case 5:
         * // Quitter
         * System.out.println("Au revoir !");
         * h2WebServer.stop();
         * // Fermer le serveur H2
         * 
         * scanner.close();
         * 
         * break;
         * 
         * default:
         * System.out.println("Choix invalide. Veuillez réessayer.");
         * }
         * } while (choice != 5);
         */

    }
}
