package org.theaz.karabookapi.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* TODO: Need to research this feature and refactor dev_access_token check
*/
// @Component
//public class DevAccessFilter implements Filter {
//
//    @Value("${dev.static.token}")
//    private String staticDevToken;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        String method = httpRequest.getMethod();
//        String uri = httpRequest.getRequestURI();
//
//        String devAccessToken = httpRequest.getHeader("dev_access_token");
//
//        // Перевірка на збіг токенів
//        if (!staticDevToken.equals(devAccessToken)) {
//            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid dev access token");
//            return;
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // Може залишитись порожнім, якщо не потрібно додаткового очищення ресурсів
//    }
//}
