import config.HibernateConfiguration;
import controller.HibernateController;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        HibernateController hc = new HibernateController();
        hc.addUser("test1", "test1", "test1@test.pl", "test1");
    }
}
