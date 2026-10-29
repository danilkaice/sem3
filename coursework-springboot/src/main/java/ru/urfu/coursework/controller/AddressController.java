package ru.urfu.coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.coursework.dto.AddressDto;
import ru.urfu.coursework.entity.Address;
import ru.urfu.coursework.entity.Employee;
import ru.urfu.coursework.repository.AddressRepository;
import ru.urfu.coursework.repository.EmployeeRepository;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    private final AddressRepository addressRepo;
    private final EmployeeRepository employeeRepo;

    public AddressController(AddressRepository addressRepo, EmployeeRepository employeeRepo) {
        this.addressRepo = addressRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/list/{employeeId}")
    public String list(@PathVariable Long employeeId, Model model) {
        model.addAttribute("addresses", addressRepo.findByEmployeeId(employeeId));
        model.addAttribute("employeeId", employeeId);
        return "address-list";
    }

    @GetMapping("/add/{employeeId}")
    public String addForm(@PathVariable Long employeeId, Model model) {
        AddressDto dto = new AddressDto();
        dto.setEmployeeId(employeeId);
        model.addAttribute("address", dto);
        return "address-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("address") AddressDto dto) {
        Address addr = new Address();
        addr.setCity(dto.getCity());
        addr.setStreet(dto.getStreet());
        addr.setHouse(dto.getHouse());
        addr.setApartment(dto.getApartment());
        Employee emp = employeeRepo.findById(dto.getEmployeeId()).orElseThrow();
        addr.setEmployee(emp);
        addressRepo.save(addr);
        return "redirect:/addresses/list/" + dto.getEmployeeId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Address addr = addressRepo.findById(id).orElseThrow();
        Long empId = addr.getEmployee().getId();
        addressRepo.deleteById(id);
        return "redirect:/addresses/list/" + empId;
    }
}