package com.nxp.test_spring_secur.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String PROTECTED_URLS = "/nxp/v1/account/**";

    @Autowired
    AuthenticationProvider provider;

    //Cách check token, thông tin login
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("zo configure(final AuthenticationManagerBuilder");
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig configure");
        http.antMatcher(PROTECTED_URLS)
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/nxp/v1/account/loginAc").permitAll()
                .antMatchers("/nxp/v1/account/cre").permitAll()
//                .hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/nxp/v1/account/loginAc").permitAll()
                .and().csrf().disable();
    }
    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception{
        System.out.println("zo authenticationFilter");
        final AuthenticationFilter filter = new AuthenticationFilter(new AntPathRequestMatcher(PROTECTED_URLS));
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
