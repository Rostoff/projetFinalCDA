package com.christophe.backend.secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    //Pour montrer à spring ou chercher les utilisateurs et les rôles
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("{noop}1234").roles("USER");
    }

    @Override
    //C'est ici qu'on va définir les droits d'accès
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable(); //desactiver le synchronize token
        http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
