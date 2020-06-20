package controller;

import config.HibernateConfiguration;
import model.User;
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
}
