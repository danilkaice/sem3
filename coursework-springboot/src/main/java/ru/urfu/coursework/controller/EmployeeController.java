package ru.urfu.coursework.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.coursework.dto.EmployeeDto;
import ru.urfu.coursework.entity.Employee;
import ru.urfu.coursework.entity.User;
import ru.urfu.coursework.repository.EmployeeRepository;
import ru.urfu.coursework.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepo;
    private final UserService userService;

    public EmployeeController(EmployeeRepository employeeRepo, UserService userService) {
        this.employeeRepo = employeeRepo;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        List<Employee> employees;

        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
        boolean isUser = currentUser.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ROLE_USER"));

        if (isAdmin) {
            employees = employeeRepo.findAll();
        } else if (isUser) {
            employees = employeeRepo.findByOwner(currentUser);
        } else {
            employees = employeeRepo.findAll();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("currentUser", currentUser);
        return "employees-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") EmployeeDto dto,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User owner = userService.findByEmail(userDetails.getUsername());
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setSurname(dto.getSurname());
        emp.setName(dto.getName());
        emp.setAge(dto.getAge());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());
        emp.setOwner(owner);
        employeeRepo.save(emp);
        return "redirect:/employees/list";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Employee emp = employeeRepo.findById(id).orElseThrow();
        EmployeeDto dto = new EmployeeDto();
        dto.setId(emp.getId());
        dto.setSurname(emp.getSurname());
        dto.setName(emp.getName());
        dto.setAge(emp.getAge());
        dto.setDepartment(emp.getDepartment());
        dto.setSalary(emp.getSalary());
        model.addAttribute("employee", dto);
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeRepo.deleteById(id);
        return "redirect:/employees/list";
    }

    @GetMapping("/calculate/{id}")
    public String calculateBonus(@PathVariable Long id, Model model) {
        Employee emp = employeeRepo.findById(id).orElseThrow();
        double bonus = emp.getSalary() * 0.15 * 12;
        model.addAttribute("employee", emp);
        model.addAttribute("bonus", bonus);
        return "bonus-result";
    }
}