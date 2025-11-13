package com.hoteljulia.core.startup;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hoteljulia.core.model.users.Employee;
import com.hoteljulia.core.model.users.User;
import com.hoteljulia.core.repository.users.UserRepository;


@Component
public class StartupRunner implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public StartupRunner(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        String email = "jhcs2025@hoteljulia.com";
        String password = "HotelJuliaManager1";
        
        if (userRepo.existsByEmail(email)) return;

        insertEmployee(
            "Jules Hope Sean", password, email, "09183947295",
            "Some random address", User.Role.MANAGER,
            BigDecimal.valueOf(1000000)
        );
    }

    public User insertEmployee(
        String name, String password, String email, String phoneNumber, 
        String address, User.Role role, BigDecimal salary) {

        Employee employee = new Employee();
        
        employee.setName(name);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setAddress(address);
        employee.setRole(role);
        employee.setSalary(salary);

        return userRepo.save(employee);
    }
}