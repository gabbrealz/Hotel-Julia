package com.hoteljulia.core.repository.users;

import com.hoteljulia.core.model.users.Employee;
import com.hoteljulia.core.model.users.Guest;
import com.hoteljulia.core.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query(value = """
        SELECT user.*, guest.*
        FROM users.guest guest
        JOIN users.user user ON user.id = guest.id
        WHERE guest.id = :id
        """, nativeQuery = true)
    Optional<Guest> findGuestById(@Param("id") Long id);

    @Query(value = """
        SELECT user.*, staff.*
        FROM users.staff staff
        JOIN users.user user ON user.id = staff.id
        WHERE staff.id = :id
        """, nativeQuery = true)
    Optional<Employee> findEmployeeById(@Param("id") Long id);

}