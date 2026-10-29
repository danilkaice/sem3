package ru.urfu.coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.coursework.repository.UserRepository;
import ru.urfu.coursework.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepo;
    private final UserService userService;

    public AdminController(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "admin-users";
    }

    @PostMapping("/assign-role")
    public String assignRole(@RequestParam String email, @RequestParam String role) {
        userService.assignRole(email, role);
        return "redirect:/admin/users";
    }
}