package com.home.todoList.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from users as u, user_roles as r where u.id = r.user_id and u.username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginme", "/api/user").permitAll()
                .antMatchers("/resources/css/**").permitAll()
                .antMatchers("/app/**", "/task/**", "/api/user/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
                .and()
            .rememberMe().and()
            .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/loginme")
                .permitAll()
                .defaultSuccessUrl("/home", true)
//                    .failureUrl("/failure.jsp")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logoff")
                .logoutSuccessUrl("/login?logout")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
            .and()

//            .sessionManagement()
//                .sessionAuthenticationStrategy(concurrentSessionControlStrategy(sessionRegistry()))
//                .maximumSessions(1)
//                .sessionRegistry(sessionRegistry())
//            .and()
//                .addFilter(usernamePasswordAuthenticationFilter())
//                .addFilterAfter(concurrentSessionFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()

        ;
    }

    @Bean(name = "sessionRegistry")
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        UsernamePasswordAuthenticationFilter upaf = new UsernamePasswordAuthenticationFilter();
        upaf.setSessionAuthenticationStrategy(sessionFixationProtectionStrategy());
        try {
            upaf.setAuthenticationManager(authenticationManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upaf;
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter(){
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry(),"/session-expired.html");
        return concurrentSessionFilter;
    }

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlStrategy(SessionRegistry sessionRegistry){
        ConcurrentSessionControlAuthenticationStrategy cscs = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        cscs.setMaximumSessions(1);
        cscs.setExceptionIfMaximumExceeded(true);
        return cscs;
    }

    @Bean()
    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy(){
        return new SessionFixationProtectionStrategy();
    }

    @Bean
    RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy(){
        return  new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy(){
        List<SessionAuthenticationStrategy> delegates = new ArrayList<>();
        delegates.add(concurrentSessionControlStrategy(sessionRegistry()));
        delegates.add(sessionFixationProtectionStrategy());
        delegates.add(registerSessionAuthenticationStrategy());
        CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy = new CompositeSessionAuthenticationStrategy(delegates);
        return compositeSessionAuthenticationStrategy;
    }
}
