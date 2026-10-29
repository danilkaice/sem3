package ru.urfu.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.coursework.entity.Address;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmployeeId(Long employeeId);
}