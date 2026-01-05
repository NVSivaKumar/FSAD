package klu.Hibernate_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {

        System.out.println("Loading Hibernate configuration...");

        // Load configuration from resources automatically
        Configuration cfg = new Configuration().configure();

        // Build SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();
        System.out.println("SessionFactory created successfully!");

        // Open session and insert a record
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Employee e = new Employee();
        e.setFn("Siva");
        e.setLn("Kumar");
        e.setSal(25.5);
        session.persist(e);  // persist() ensures Hibernate 6+ compatibility
     
        tx.commit();
        session.close();
        factory.close();

        System.out.println("Table created and record inserted successfully!");
    }
}
