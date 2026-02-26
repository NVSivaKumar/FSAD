package klu.Hibernate_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // 1️⃣ Hibernate configuration
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/hibernate_crud_sec15?useSSL=false&serverTimezone=UTC");
        cfg.setProperty("hibernate.connection.username", "root");
        cfg.setProperty("hibernate.connection.password", "root");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create"); // create table
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.format_sql", "true");
        cfg.addAnnotatedClass(Employee.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(registry);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // 2️⃣ Insert 5–8 Employee records
        List<Employee> employees = Arrays.asList(
                new Employee("Siva", "Kumar", 125.5, 95),
                new Employee("Kishore", "Kumar", 30.0, 60),
                new Employee("Aaftaab", "Ribbani Khan", 28.2, 40),
                new Employee("G", "Tribhuvan", 35.0, 70),
                new Employee("Sai", "Aditya", 55.0, 90),
                new Employee("Nikil", "", 20.0, 100),
                new Employee("Subramanyam", "yasawin", 20.0, 100)
        );

        for (Employee e : employees) {
            session.persist(e);
        }
        tx.commit();

        System.out.println("\n=== Employees inserted ===\n");

        // =========================
        // 3️⃣ HQL Queries
        // =========================

        // a. All employees sorted by salary ascending
        System.out.println("Employees by salary ascending:");
        session.createQuery("FROM Employee ORDER BY sal ASC", Employee.class)
                .list().forEach(System.out::println);

        // b. All employees sorted by salary descending
        System.out.println("\nEmployees by salary descending:");
        session.createQuery("FROM Employee ORDER BY sal DESC", Employee.class)
                .list().forEach(System.out::println);

        // 4️⃣ Sort employees by quantity descending
        System.out.println("\nEmployees by quantity (highest first):");
        session.createQuery("FROM Employee ORDER BY quantity DESC", Employee.class)
                .list().forEach(System.out::println);

        // 5️⃣ Pagination: first 3 and next 3
        System.out.println("\nFirst 3 employees (pagination):");
        session.createQuery("FROM Employee ORDER BY id ASC", Employee.class)
                .setFirstResult(0).setMaxResults(7).list()
                .forEach(System.out::println);

        System.out.println("\nNext 3 employees (pagination):");
        session.createQuery("FROM Employee ORDER BY id ASC", Employee.class)
                .setFirstResult(7).setMaxResults(0y).list()
                .forEach(System.out::println);

        // 6️⃣ Aggregate operations
        Long total = session.createQuery("SELECT COUNT(*) FROM Employee", Long.class).uniqueResult();
        System.out.println("\nTotal employees: " + total);

        Long qtyMoreThan0 = session.createQuery("SELECT COUNT(*) FROM Employee WHERE quantity > 0", Long.class)
                .uniqueResult();
        System.out.println("Employees with quantity > 0: " + qtyMoreThan0);

        System.out.println("\nCount of employees grouped by last name:");
        session.createQuery("SELECT ln, COUNT(*) FROM Employee GROUP BY ln", Object[].class)
                .list().forEach(row -> System.out.println(row[0] + " -> " + row[1]));

        Double minSalary = session.createQuery("SELECT MIN(sal) FROM Employee", Double.class).uniqueResult();
        Double maxSalary = session.createQuery("SELECT MAX(sal) FROM Employee", Double.class).uniqueResult();
        System.out.println("\nMinimum salary: " + minSalary + ", Maximum salary: " + maxSalary);

        // 7️⃣ Group by last name
        System.out.println("\nEmployees grouped by last name:");
        session.createQuery("SELECT ln, COUNT(*) FROM Employee GROUP BY ln", Object[].class)
                .list().forEach(row -> System.out.println(row[0] + " -> " + row[1]));

        // 8️⃣ Salary range filter (e.g., 25 to 50)
        System.out.println("\nEmployees with salary between 25 and 50:");
        session.createQuery("FROM Employee WHERE sal BETWEEN 25 AND 50", Employee.class)
                .list().forEach(System.out::println);

        // 9️⃣ LIKE queries
        System.out.println("\nEmployees first name starting with 'S':");
        session.createQuery("FROM Employee WHERE fn LIKE 'S%'", Employee.class)
                .list().forEach(System.out::println);

        System.out.println("\nEmployees first name ending with 'a':");
        session.createQuery("FROM Employee WHERE fn LIKE '%a'", Employee.class)
                .list().forEach(System.out::println);

        System.out.println("\nEmployees first name containing 'i':");
        session.createQuery("FROM Employee WHERE fn LIKE '%i%'", Employee.class)
                .list().forEach(System.out::println);

        System.out.println("\nEmployees first name with exact length 5:");
        session.createQuery("FROM Employee WHERE LENGTH(fn) = 5", Employee.class)
                .list().forEach(System.out::println);

        session.close();
        factory.close();
        System.out.println("\n=== All HQL queries executed successfully ===");
        System.out.println("Done");
    }
}
