package com.name.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.name.jwt.filters.JwtRequestFilter;
import com.name.jwt.models.AuthenticationRequest;
import com.name.jwt.models.AuthenticationResponse;
import com.name.jwt.service.MyUserDetailsService;
import com.name.jwt.util.JwtUtil;

//@SpringBootApplication
//public class SpringSecurityJwtApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringSecurityJwtApplication.class, args);
//	}
//
//}

@RestController
class HelloWorldController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	public String getToken() throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken("foo", "foo")
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername("foo");

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return jwt;
	}

}

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtUtil jwtTokenUtil;
//
//	@Autowired
//	private MyUserDetailsService userDetailsService;
//	
//	public String getToken() throws Exception {
//
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken("foo", "foo")
//			);
//		}
//		catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}
//
//
//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername("foo");
//
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//		return jwt;
//	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("configure(HttpSecurity httpSecurity)");
		
		// TODO:: remove in production
		httpSecurity.headers().frameOptions().disable();
		
		httpSecurity.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/api/v1/**").permitAll()
					.antMatchers("/api/chess/v1/*").permitAll()
					.antMatchers("/api/shop/v1/image/**").permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.exceptionHandling()
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
