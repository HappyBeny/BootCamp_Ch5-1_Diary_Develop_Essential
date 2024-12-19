package com.example.ch3_2_diary.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/members/signup", "/members/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
