package com.adrianm.jwttokenstudy.rest.filters;

import com.adrianm.jwttokenstudy.services.AuthenticationService;
import jakarta.servlet.*;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    private final AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((RequestFacade) servletRequest).getRequestURI();

        if ("/auth".equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String bearerToken = ((RequestFacade) servletRequest).getHeader("authorization");

            authenticationService.verifyToken(bearerToken);

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public AuthenticationFilter(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
