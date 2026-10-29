package ru.urfu.coursework.dto;

import jakarta.validation.constraints.*;

public class EmployeeDto {
    private Long id;

    @NotEmpty(message = "Фамилия обязательна")
    private String surname;

    @NotEmpty(message = "Имя обязательно")
    private String name;

    @Min(value = 18, message = "Минимум 18 лет")
    @Max(value = 100, message = "Максимум 100 лет")
    private int age;

    @NotEmpty(message = "Отдел обязателен")
    private String department;

    @Min(value = 0, message = "Оклад не может быть отрицательным")
    private double salary;

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
}