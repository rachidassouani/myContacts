package org.glsid.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("123").roles("user");
		
		auth.inMemoryAuthentication()
			.withUser("admin").password("123").roles("admin", "user");
			
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/Index");
		http.authorizeRequests().antMatchers("/Index").hasRole("user");
		http.authorizeRequests().antMatchers("/Adding", "/Updating", "/Delete").hasRole("admin");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}






