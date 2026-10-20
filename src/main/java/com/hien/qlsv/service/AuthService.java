package com.hien.qlsv.service;

import com.hien.qlsv.dto.LoginDto;
import com.hien.qlsv.dto.RegisterDto;
import com.hien.qlsv.entity.User;
import com.hien.qlsv.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// SERVICE = noi chua LOGIC nghiep vu (kiem tra trung, ma hoa mat khau...).
// Controller khong goi thang Repository, ma goi qua Service.
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Spring tu "tiem" (inject) UserRepository vao day qua constructor
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Mật khẩu nhập lại không khớp");
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        // DTO -> Entity
        User user = new User();
        user.setHoTen(dto.getHoTen());
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));   // ma hoa truoc khi luu
        userRepository.save(user);
    }

    public User login(LoginDto dto) {
        return userRepository.findByUsername(dto.getUsername())
                // so mat khau nhap vao voi chuoi BCrypt trong DB
                .filter(u -> encoder.matches(dto.getPassword(), u.getPassword()))
                // khong noi ro sai username hay password -> kho do tai khoan hon
                .orElseThrow(() -> new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu"));
    }
}
