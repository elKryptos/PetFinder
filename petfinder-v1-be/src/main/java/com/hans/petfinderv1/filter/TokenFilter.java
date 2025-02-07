package com.hans.petfinderv1.filter;

import com.hans.petfinderv1.exception.NotFoundException;
import com.hans.petfinderv1.services.TokenBlacklistService;
import com.hans.petfinderv1.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
@RequiredArgsConstructor
public class TokenFilter implements Filter {

    private final TokenUtil tokenUtil;
    private final TokenBlacklistService tokenBlacklistService;

    //TODO IMPLEMENT CONTROL FILTER
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        if ("/auth/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Jws<Claims> claimsJws = tokenUtil.allClaimsJws(token);
                if (tokenBlacklistService.isTokenBlacklisted(token)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is blacklisted");
                    return;
                }
                if (claimsJws == null) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return ;
                }
                request.setAttribute("claims", claimsJws);
            } catch (NotFoundException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }



}
