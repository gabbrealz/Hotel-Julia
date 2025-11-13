package com.hoteljulia.web.config.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.hoteljulia.core.model.users.CustomUserDetails;
import com.hoteljulia.core.model.users.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        User.Role userRole = ((CustomUserDetails) authentication.getPrincipal()).getUser().getRole();

        String redirectURL = request.getContextPath();

        switch (userRole) {
            case MANAGER -> redirectURL += "/manager/dashboard";
            case RECEPTIONIST -> redirectURL += "/receptionist/dashboard";
            case HOUSEKEEPING -> redirectURL += "/housekeeping/dashboard";
            case MAINTENANCE -> redirectURL += "/maintenance/dashboard";

            default -> {
                SavedRequest savedRequest = requestCache.getRequest(request, response);
                requestCache.removeRequest(request, response);

                if (savedRequest == null) redirectURL += "/";
                else redirectURL = savedRequest.getRedirectUrl();
            }
        }

        response.sendRedirect(redirectURL);
    }
}