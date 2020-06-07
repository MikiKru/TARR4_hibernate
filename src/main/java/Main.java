import config.HibernateConfiguration;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // otwarcie sesji
        Session session = HibernateConfiguration.getSessionFactory().openSession();

        session.close();
    }
}
