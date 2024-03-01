package com.visa.connector.security;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 The filter is responsible for verifying the JWT token
 */
public class JwtFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, AuthenticationException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("Authorization");

        // validate token
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            logger.error("Authentication failed: Missing token in header for request:{}", request);
            throw new AuthenticationException("Authentication failed: Missing token in header") {
            };
        }

        // pass token
        final String token = authHeader.substring(7);
        Claims claims = JwtUtil.parseToken(token);

        // add claims for subsequent process
        request.setAttribute("claims", claims);

        filterChain.doFilter(request, response);
    }
}
