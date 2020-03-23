package com.app.barber.config;

import com.app.barber.security.JwtAuthenticationEntryPoint;
import com.app.barber.security.JwtAuthenticationFilter;
import com.app.barber.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Order(2)
    @Configuration
    class JwtAuth extends WebSecurityConfigurerAdapter{

        private UserDetailsServiceImpl userDetailsService;
        private JwtAuthenticationEntryPoint entryPoint;

        @Autowired
        public JwtAuth(UserDetailsServiceImpl userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/")
                    .authorizeRequests()
                        .antMatchers("/api/barber/**").permitAll()
                        .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                    .addFilter(new JwtAuthenticationFilter())
                        .exceptionHandling()
                        .authenticationEntryPoint(entryPoint)
                        .and()
                    .csrf().disable();

            http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Order(1)
    @Configuration
    class OpenIdAuth extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/reviews")
                    .authorizeRequests()
                    .antMatchers("/reviews").authenticated();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter(){
        return new JwtAuthenticationFilter();
    }
}
