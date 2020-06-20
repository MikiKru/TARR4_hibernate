import config.HibernateConfiguration;
import controller.HibernateController;
import org.hibernate.Session;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HibernateController hc = new HibernateController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("APLIKACJA BLOGA");
        boolean isLogged = false;
        while (true) {
            if (!isLogged) {
                System.out.println("Podaj login:");
                String login = scanner.nextLine();
                System.out.println("Podaj hasło:");
                String password = scanner.nextLine();
                    
            } else {
                System.out.println("Co chcesz zrobić?");
                System.out.println("1. Dodaj posta \n2. Zmień tutuł post \n3. Usuń posta \nQ. Wyjdź");
                String decision = scanner.nextLine();
                if (decision.toUpperCase().equals("Q")){
                    break;
                }
            }

        }


//        hc.addUser("test1", "test1", "test1@test.pl", "test1");
    }
}
