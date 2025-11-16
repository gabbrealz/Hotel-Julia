package com.hoteljulia.core.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "users", name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    
    public User() {
        this.role = Role.GUEST;
    }

    public void setRole(Role role) { if (role != null) this.role = role; }


    public enum Role {
        GUEST,
        MANAGER,
        RECEPTIONIST,
        HOUSEKEEPING,
        MAINTENANCE;
    }
}