import config.HibernateConfiguration;
import controller.HibernateController;
import model.CategoryEnum;
import model.Post;
import model.User;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HibernateController hc = new HibernateController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("APLIKACJA BLOGA");
        boolean isLogged = false;
        User user = null;
        while (true) {
            if (!isLogged) {
                System.out.println("Podaj e-mail:");
                String login = scanner.nextLine();
                System.out.println("Podaj hasło:");
                String password = scanner.nextLine();
                user = hc.loginUser(login, password);
                if(user != null){
                    isLogged = true;
                }
            } else {
                System.out.println("Co chcesz zrobić?");
                System.out.println("1. Dodaj posta \n2. Zmień tutuł post \n3. Usuń posta \nQ. Wyjdź");
                String decision = scanner.nextLine();
                if (decision.equals("1")) {
                    System.out.println("Podaj tytuł: ");
                    String title = scanner.nextLine();
                    System.out.println("Podaj treść: ");
                    String content = scanner.nextLine();
                    System.out.println("Wybierz kategorię: ");
                    Arrays.stream(CategoryEnum.values()).forEach(System.out::println);
                    CategoryEnum categoryEnum = CategoryEnum.valueOf(scanner.nextLine());
                    Date dateTime = new Date();
                    Post post = new Post(title, content, categoryEnum, dateTime, user);
                    hc.addPost(post);
                } else if (decision.equals("2")) {

                } else if (decision.equals("3")){

                } else if (decision.toUpperCase().equals("Q")){
                    break;
                } else {
                    System.out.println("Błędny wybór");
                }
            }

        }


//        hc.addUser("test1", "test1", "test1@test.pl", "test1");
    }
}
