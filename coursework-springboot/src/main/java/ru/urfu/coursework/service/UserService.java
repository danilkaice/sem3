package ru.urfu.coursework.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.urfu.coursework.dto.UserRegistrationDto;
import ru.urfu.coursework.entity.User;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registrationDto);
    void assignRole(String email, String roleName);
}