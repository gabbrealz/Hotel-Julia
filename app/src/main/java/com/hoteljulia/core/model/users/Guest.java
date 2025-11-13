package com.hoteljulia.core.model.users;

import com.hoteljulia.core.model.payment.PayMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(schema = "users", name = "guest")
public class Guest extends User {
    
    @Column(name = "pay_method")
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "creation_date", nullable = false, insertable = false)
    private LocalDate creationDate;


    public Guest() {
        setRole(User.Role.GUEST);
    }

    // === GETTERS AND SETTERS ===

    public PayMethod getPayMethod() { return payMethod; }
    public void setPayMethod(PayMethod payMethod) { this.payMethod = payMethod; }

    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
}
