package controller;

import config.HibernateConfiguration;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateController {
    public void addUser(String name, String lastName, String email, String password){
        // otwarcie sesji
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        // rozpoczęcie transakcji
        Transaction transaction =session.beginTransaction();
        session.save(new User(name,lastName,email,password));
        transaction.commit();
        session.close();
    }
    public User loginUser(String login, String password){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();

        Query query = session.createQuery("SELECT u FROM User u WHERE u.login=:login AND u.password=:password");
        query.setString("login", login);
        query.setString("password", password);
        query.setMaxResults(1);
        User user = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }
}
