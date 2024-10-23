import org.h2.tools.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws Exception { // Ajout de l'exception

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Server h2WebServer = context.getBean("h2WebServer", Server.class);

        h2WebServer.start(); 
        String h2ConsoleUrl = h2WebServer.getURL();

        logger.info("H2 Console URL: {}", h2ConsoleUrl);
        System.out.println(h2ConsoleUrl);


    }
}
