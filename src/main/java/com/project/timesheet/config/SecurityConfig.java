package com.project.timesheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

import static com.project.timesheet.config.Roles.ROLE_ADMIN;
import static com.project.timesheet.config.Roles.ROLE_USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1")).roles("ROLE_USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2")).roles("ROLE_USER")
//                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles(ROLE_ADMIN.getRoleName());
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, 1 FROM user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, roles FROM user WHERE username = ?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ROLE_ADMIN.getRoleName())
                .antMatchers("/user/**").hasRole(ROLE_USER.getRoleName())
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/")
                .loginProcessingUrl("/perform_login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                //.failureUrl("/login.html?error=true")
//                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler(logoutSuccessHandler())
        ;
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return null;
    }
//    private AuthenticationFailureHandler authenticationFailureHandler() {
//        return null;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}