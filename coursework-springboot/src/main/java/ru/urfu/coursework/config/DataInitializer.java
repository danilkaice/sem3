package ru.urfu.coursework.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.urfu.coursework.entity.Role;
import ru.urfu.coursework.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepo;

    public DataInitializer(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) {
        if (roleRepo.findByName("ROLE_READ_ONLY") == null)
            roleRepo.save(new Role(null, "ROLE_READ_ONLY"));
        if (roleRepo.findByName("ROLE_USER") == null)
            roleRepo.save(new Role(null, "ROLE_USER"));
        if (roleRepo.findByName("ROLE_ADMIN") == null)
            roleRepo.save(new Role(null, "ROLE_ADMIN"));
    }
}