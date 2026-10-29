package ru.urfu.coursework.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String house;
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Address() {}

    public Address(Long id, String city, String street, String house, String apartment, Employee employee) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.employee = employee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getHouse() { return house; }
    public void setHouse(String house) { this.house = house; }
    public String getApartment() { return apartment; }
    public void setApartment(String apartment) { this.apartment = apartment; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}