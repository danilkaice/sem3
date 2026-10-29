package ru.urfu.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.coursework.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}