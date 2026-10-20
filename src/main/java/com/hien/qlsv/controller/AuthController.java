package com.hien.qlsv.controller;

import com.hien.qlsv.dto.LoginDto;
import com.hien.qlsv.dto.RegisterDto;
import com.hien.qlsv.entity.User;
import com.hien.qlsv.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// CONTROLLER = nhan request tu trinh duyet, goi Service, chon View (file HTML) tra ve.
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());   // form rong
        return "login";                                   // -> templates/login.html
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto dto, BindingResult result,
                        HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "login";   // quay lai form, hien loi tung o
        }
        try {
            User user = authService.login(dto);
            // Luu thong tin dang nhap vao session (khong luu ca User vi co password)
            session.setAttribute("username", user.getUsername());
            session.setAttribute("hoTen", user.getHoTen());
            return "redirect:/students";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDto") RegisterDto dto, BindingResult result,
                           Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            authService.register(dto);
            ra.addFlashAttribute("msg", "Đăng ký thành công, mời đăng nhập");   // hien 1 lan sau redirect
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
