package ru.urfu.coursework.dto;

import jakarta.validation.constraints.*;

public class AddressDto {
    private Long id;

    @NotEmpty(message = "Город обязателен")
    private String city;

    @NotEmpty(message = "Улица обязательна")
    private String street;

    @NotEmpty(message = "Дом обязателен")
    private String house;

    private String apartment;
    private Long employeeId;

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
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
}