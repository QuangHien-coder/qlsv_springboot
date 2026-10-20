package com.hien.qlsv.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// "Nguoi gac cong": moi request vao /students/** deu bi kiem tra truoc khi toi Controller.
// Chua dang nhap (session khong co username) -> day ve /login.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
                var session = req.getSession(false);
                if (session == null || session.getAttribute("username") == null) {
                    res.sendRedirect("/login");
                    return false;   // chan, khong cho vao Controller
                }
                return true;
            }
        }).addPathPatterns("/students/**", "/students");
    }
}
