package ru.urfu.coursework.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String name;
    private int age;
    private String department;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Employee() {}

    public Employee(Long id, String surname, String name, int age, String department, double salary, User owner) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
        this.owner = owner;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}