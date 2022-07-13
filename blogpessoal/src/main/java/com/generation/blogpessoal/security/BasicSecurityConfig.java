package com.generation.blogpessoal.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//para não dar warning
//@SuppressWarnings("deprecation")
//@EnableWebSecurity

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private UserDetailsService userDetailsService;
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
     }
	
	/*
	 * criando o usuario em memoria, ter acesso aos endpoints e modificalos,
	 * apenas para testes, quando terminar a aplicação tira
	 *
	 * auth.inMemoryAuthentication()
	 * .withUser("root")
	 * .password(passwordEncoder().encode ("root"))
	 * .authorities("ROLE_USER");
	 */
	
	
	  @Bean
	  public PasswordEncoder passwordEncoder(){
	  return new BCryptPasswordEncoder();
	  }
	 
	
	   
	  @Override
	  protected void configure (HttpSecurity http) throws Exception{
	          http.authorizeRequests()
	         .antMatchers("/usuarios/logar").permitAll()
	     	 .antMatchers("/usuarios/cadastrar").permitAll()
	         .antMatchers(HttpMethod.OPTIONS).permitAll()
	         .anyRequest().authenticated()
	         .and().httpBasic()
	         .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //stateless não vai guardar sessão
	         .and().cors()
	         .and().csrf().disable(); 
	  }
}
