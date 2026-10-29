package ru.urfu.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.coursework.entity.Employee;
import ru.urfu.coursework.entity.User;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByOwner(User owner);
}