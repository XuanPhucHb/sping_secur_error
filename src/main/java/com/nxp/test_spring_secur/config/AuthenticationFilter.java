package com.nxp.test_spring_secur.config;

import com.nxp.test_spring_secur.repo.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

//    protected AuthenticationFilter(String defaultFilterProcessesUrl) {
//        super(defaultFilterProcessesUrl);
//    }

    protected AuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        System.out.println("attemptAuthentication");
        String token = httpServletRequest.getHeader(AUTHORIZATION);

        if (token != null) {
            System.out.println("token:" + token);
            Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
            return getAuthenticationManager().authenticate(requestAuthentication);
        }
        throw new UsernameNotFoundException("token not found");
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
