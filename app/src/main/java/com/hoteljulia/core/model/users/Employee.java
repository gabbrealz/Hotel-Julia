package com.hoteljulia.core.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@DynamicInsert
@Table(schema = "users", name = "staff")
public class Employee extends User {

    private BigDecimal salary;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate = LocalDate.now();

    // === GETTERS AND SETTERS ===

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
}