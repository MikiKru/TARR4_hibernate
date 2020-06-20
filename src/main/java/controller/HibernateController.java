package controller;

import config.HibernateConfiguration;
import model.Post;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


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
        Query query = session.createQuery(
                "SELECT u FROM User u WHERE u.userEmail=:login AND u.userPassword=:password");
        query.setString("login", login);
        query.setString("password", password);
        query.setMaxResults(1);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }
    public void addPost(Post post){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();
    }
    public void findUserPosts(User user){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        Query query = session.createQuery("SELECT p FROM Post p WHERE p.user=:user");
        query.setParameter("user", user);
        List<Post> userPosts = query.list();
        userPosts.stream()
                .forEach(post -> System.out.println(
                        post.getPostId() + " " +post.getPostTitle()));
        transaction.commit();
        session.close();
    }
    public Post findUserPostById(int postId, User user){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        Query query = session.createQuery("SELECT p FROM Post p WHERE p.user=:user AND p.postId=:id");
        query.setParameter("user", user);
        query.setInteger("id", postId);
        query.setMaxResults(1);
        Post post = (Post) query.uniqueResult();
        transaction.commit();
        session.close();
        return post;
    }
    public void updatePostTitle(Post oldPost, String newTitle){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        oldPost.setPostTitle(newTitle);
        session.saveOrUpdate(oldPost);      // UPDATE
        transaction.commit();
        session.close();
    }
    public void deleteUserPostById(int postId, User user){
        Post postToDelete = findUserPostById(postId, user);
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        session.delete(postToDelete);
        transaction.commit();
        session.close();
    }
    public void printAllPosts(){
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM Post p");
        List<Post> userPosts = query.list();
        userPosts.stream()
                .forEach(post -> System.out.printf(
                        "| %2d | %20s | %20s | %20s | %15s |\n",
                        post.getPostId(),
                        post.getPostTitle(),
                        post.getUser().getUserName() + " " + post.getUser().getUserLastName(),
                        post.getPostCategory(),
                        post.getPostRegistration()));
        transaction.commit();
        session.close();
    }
}
