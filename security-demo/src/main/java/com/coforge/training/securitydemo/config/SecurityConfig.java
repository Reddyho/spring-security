
package com.coforge.training.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 *Author:Koppula.Reddy
 *Date  :Dec 3, 2024
 *Time   :9:43:12 AM
 *
 */
/*
 * @Configuration is an annotation used to mark a class that contains one or more 
 * @Bean method declarations. 
 * This annotation is used to configure the application context by creating and 
 * returning either a single instance of a bean or a set of instances. 
 * The @Configuration class is primarily used for declarative configuration of 
 * the application context. 
 * When used in combination with @Bean, it allows the developer to define the 
 * components and beans needed to build the application. 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/*
	 * The @Bean annotation is used to indicate that a method instantiates, 
	 * configures, and initializes a new object to be managed by 
	 * the Spring IoC container. 
	 */

	/*
	 * This method is used to provide a custom AuthenticationManager bean. 
	 * The AuthenticationManager is a core interface in Spring Security that is responsible for processing 
	 * authentication requests.
	 * Parameters:
	 * AuthenticationConfiguration authConfig: This is a configuration class that allows access to the 
	 * AuthenticationManager.
	 * Return: An instance of AuthenticationManager obtained from the provided AuthenticationConfiguration.
	 * Throws: An Exception if there is a problem retrieving the AuthenticationManager.
	 */

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception {
		return authconfig.getAuthenticationManager();

	}
	/*
	 * This method defines a bean for PasswordEncoder. 
	 * The PasswordEncoder interface is used for encoding passwords in a secure manner before storing them.
	 * Return: An instance of BCryptPasswordEncoder, which is a strong hashing algorithm that applies 
	 * the BCrypt algorithm for encoding passwords.
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * This method defines a bean for PasswordEncoder. 
	 * The PasswordEncoder interface is used for encoding passwords in a secure manner before storing them.
	 * Return: An instance of BCryptPasswordEncoder, which is a strong hashing algorithm that applies 
	 * the BCrypt algorithm for encoding passwords.
	 */


	/*
	 * This method configures the security filter chain, which defines how HTTP requests are secured. 
	 * The SecurityFilterChain is a core component of Spring Security that manages the filtering of HTTP 
	 * requests based on the security configurations.
		Parameters:
		HttpSecurity http: The main class used for configuring web-based security for specific HTTP requests.
		Return: A configured SecurityFilterChain object that applies the security rules defined within the method.
		Throws: An Exception if there is a problem configuring the SecurityFilterChain.

		Configuration Steps:
		CSRF: Disables Cross-Site Request Forgery protection using csrf.disable().
		Authorize Requests:
			Permits all requests to /api/register and /api/login.
		Requires authentication for all other requests.
		Form Login:
			Specifies a custom login page located at /login.
			Allows all users to access the login page.
		Logout:
			Configures the logout functionality with a custom logout URL (/logout).
			Permits all users to log out.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				//.requestMatchers("/api/register", "/api/login","/api/users").permitAll()
				.requestMatchers("/api/register", "/api/login","/api/users").permitAll() //securing endpoints
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				)
		.logout(logout -> logout
				.logoutUrl("/logout")
				.permitAll()
				);

		return http.build();
	}

}
