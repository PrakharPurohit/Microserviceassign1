package com.lcwd.user.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // for enabling role base authorization
public class SecurityConfig {
	
	@Autowired
	private AppBasicAuthenticationEntryPoint authenticationEntrypoint;
	
	// authentication 
	//bean for userdetails - and config username , password , role
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin= User.withUsername("prakhar")
				.password(encoder.encode("p2000"))
				.roles("ADMIN")
				.build();
		UserDetails user= User.withUsername("Jhon")
				.password(encoder.encode("j2000"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	// bean - password encoder
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// to authenticate different methods - for /usersId if it maches url pattern then only permit
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests()
					.anyRequest().authenticated()
					.and().httpBasic()
					.authenticationEntryPoint(authenticationEntrypoint);
			return http.build();
		}
	
		
//		http.authorizeRequests()
//		.antMatchers("api/users").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.httpBasic()
//		.authenticationEntryPoint(authenticationEntryPoint);
//	return http.build();	
	}

