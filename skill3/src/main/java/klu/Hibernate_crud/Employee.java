package klu.Hibernate_crud;

import jakarta.persistence.*;

@Entity
@Table(name = "skill3")
@Access(AccessType.FIELD)  // Preserve field order
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fn")
    private String fn;

    @Column(name = "ln")
    private String ln;

    @Column(name = "sal")
    private double sal;

    @Column(name = "quantity")
    private int quantity;  // Added quantity to demonstrate sorting/pagination

    public Employee() {}

    public Employee(String fn, String ln, double sal, int quantity) {
        this.fn = fn;
        this.ln = ln;
        this.sal = sal;
        this.quantity = quantity;
    }

    // Getters
    public int getId() { return id; }
    public String getFn() { return fn; }
    public String getLn() { return ln; }
    public double getSal() { return sal; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setFn(String fn) { this.fn = fn; }
    public void setLn(String ln) { this.ln = ln; }
    public void setSal(double sal) { this.sal = sal; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fn='" + fn + '\'' +
                ", ln='" + ln + '\'' +
                ", sal=" + sal +
                ", quantity=" + quantity +
                '}';
    }
}
