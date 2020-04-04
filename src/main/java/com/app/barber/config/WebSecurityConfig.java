package com.app.barber.config;

import com.app.barber.security.jwt.JwtAuthenticationEntryPoint;
import com.app.barber.security.jwt.JwtAuthenticationFilter;
import com.app.barber.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Order(2)
    @Configuration
    public class JWTSecurity extends WebSecurityConfigurerAdapter{
        private UserDetailsServiceImpl userDetailsService;
        private JwtAuthenticationEntryPoint authenticationEntryPoint;
        private JwtAuthenticationFilter authenticationFilter;

        @Autowired
        public JWTSecurity(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint,
                                 JwtAuthenticationFilter authenticationFilter) {
            this.userDetailsService = userDetailsService;
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.authenticationFilter = authenticationFilter;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                    //.cors()
                    //.and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .csrf().disable()
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Order(1)
    @Configuration
    public class OpenIdSecurity extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/oauth/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .authorizationEndpoint();
                     //TODO
        }
    }


}
