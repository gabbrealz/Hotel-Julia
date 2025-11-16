package com.hoteljulia.web.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

	@Value("${security.rememberme.key}")
	private String rememberMeKey;

	@Value("${server.servlet.session.cookie.name}")
	private String sessionIdCookieName;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSuccessHandler successHandler) throws Exception {
		http
			.authorizeHttpRequests(requests -> requests
				.requestMatchers("/manager/**").hasRole("MANAGER")
				.requestMatchers("/receptionist/**").hasAnyRole("RECEPTIONIST", "MANAGER")
				.requestMatchers("/housekeeping/**").hasAnyRole("HOUSEKEEPING", "MANAGER")
				.requestMatchers("/maintenance/**").hasAnyRole("MAINTENANCE", "MANAGER")
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.successHandler(successHandler)
				.permitAll()
			)
			.rememberMe(remember -> remember
				.rememberMeCookieName("HOTELJULIA-REMEMBER-ME")
				.key(rememberMeKey)
				.tokenValiditySeconds(7*24*60*60)
        	)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
            	.deleteCookies(sessionIdCookieName, rememberMeKey)
				.permitAll()
			);

		return http.build();
	}
}