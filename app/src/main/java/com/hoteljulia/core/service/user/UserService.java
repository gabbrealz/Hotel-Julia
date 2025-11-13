package com.hoteljulia.core.service.user;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hoteljulia.core.model.users.User;
import com.hoteljulia.core.model.users.CustomUserDetails;


@Service
public class UserService {

    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken)
            return Optional.empty();

        return Optional.of(((CustomUserDetails) auth.getPrincipal()).getUser());
    }

    public Optional<User.Role> getCurrentRole() {
        return getCurrentUser().map(User::getRole);
    }
}