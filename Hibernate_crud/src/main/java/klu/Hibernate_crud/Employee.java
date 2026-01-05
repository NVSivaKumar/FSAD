package klu.Hibernate_crud;

import jakarta.persistence.*;

@Entity
@Table(name = "emp")
@Access(AccessType.FIELD)  // Use declared field order
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // 1️⃣ Primary key first

    @Column(name = "fn")
    private String fn;      // 2️⃣ First name

    @Column(name = "ln")
    private String ln;      // 3️⃣ Last name

    @Column(name = "sal")
    private double sal;     // 4️⃣ Salary

    // Default constructor
    public Employee() {}

    // Convenience constructor
    public Employee(String fn, String ln, double sal) {
        this.fn = fn;
        this.ln = ln;
        this.sal = sal;
    }

    // Getters
    public int getId() { return id; }
    public String getFn() { return fn; }
    public String getLn() { return ln; }
    public double getSal() { return sal; }

    // Setters
    public void setFn(String fn) { this.fn = fn; }
    public void setLn(String ln) { this.ln = ln; }
    public void setSal(double sal) { this.sal = sal; }
}
