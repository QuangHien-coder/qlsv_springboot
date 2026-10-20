package com.hien.qlsv;

import com.hien.qlsv.dto.RegisterDto;
import com.hien.qlsv.dto.StudentDto;
import com.hien.qlsv.repository.StudentRepository;
import com.hien.qlsv.repository.UserRepository;
import com.hien.qlsv.service.AuthService;
import com.hien.qlsv.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class QlsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(QlsvApplication.class, args);
    }

    // Chay 1 lan khi app khoi dong: DB trong thi them du lieu mau de co cai hien len view
    @Bean
    CommandLineRunner seed(UserRepository userRepo, StudentRepository studentRepo,
                           AuthService authService, StudentService studentService) {
        return args -> {
            if (userRepo.count() == 0) {
                RegisterDto admin = new RegisterDto();
                admin.setHoTen("Quản trị viên");
                admin.setUsername("admin");
                admin.setPassword("123456");
                admin.setConfirmPassword("123456");
                authService.register(admin);
            }
            if (studentRepo.count() == 0) {
                studentService.create(sv("SV001", "Nguyễn Văn An", "an@gmail.com", "2005-03-12", "T2608E", 8.2));
                studentService.create(sv("SV002", "Trần Thị Bình", "binh@gmail.com", "2005-07-25", "T2608E", 7.5));
                studentService.create(sv("SV003", "Lê Minh Cường", "cuong@gmail.com", "2004-11-02", "T2607M", 6.8));
                studentService.create(sv("SV004", "Phạm Thu Dung", "dung@gmail.com", "2005-01-19", "T2607M", 9.1));
                studentService.create(sv("SV005", "Hoàng Gia Huy", "huy@gmail.com", "2004-09-30", "T2608E", 5.9));
            }
        };
    }

    private static StudentDto sv(String ma, String ten, String email, String ngaySinh, String lop, double diem) {
        StudentDto dto = new StudentDto();
        dto.setMaSv(ma);
        dto.setHoTen(ten);
        dto.setEmail(email);
        dto.setNgaySinh(LocalDate.parse(ngaySinh));
        dto.setLop(lop);
        dto.setDiemTb(diem);
        return dto;
    }
}
