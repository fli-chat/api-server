package com.project.filchat.interfaces.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.filchat.common.exception.UnAuthorizedException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UnAuthorizedException e) {
            setErrorResponse(response);
        }
    }

    private void setErrorResponse(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
