package io.github.hoangtuyen04work.social_backend.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Processing request: " + req.getRequestURI());
        // Chuyển tiếp yêu cầu đến bộ lọc tiếp theo hoặc tài nguyên đích
        chain.doFilter(request, response);
        System.out.println("Request processed");
    }
}