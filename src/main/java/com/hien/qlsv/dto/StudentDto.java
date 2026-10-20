package com.hien.qlsv.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// DTO = "goi hang" di giua View <-> Controller <-> Service.
// Khac Entity: chi chua field can cho form/man hinh + luat kiem tra du lieu nhap.
public class StudentDto {

    private Long id;   // null = them moi, co gia tri = dang sua

    @NotBlank(message = "Mã SV không được để trống")
    @Size(max = 20, message = "Mã SV tối đa 20 ký tự")
    private String maSv;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên tối đa 100 ký tự")
    private String hoTen;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Past(message = "Ngày sinh phải ở quá khứ")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)   // nhan dang yyyy-MM-dd tu <input type="date">
    private LocalDate ngaySinh;

    @Size(max = 20, message = "Lớp tối đa 20 ký tự")
    private String lop;

    @NotNull(message = "Điểm TB không được để trống")
    @DecimalMin(value = "0.0", message = "Điểm từ 0 đến 10")
    @DecimalMax(value = "10.0", message = "Điểm từ 0 đến 10")
    private Double diemTb;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaSv() { return maSv; }
    public void setMaSv(String maSv) { this.maSv = maSv; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }
    public Double getDiemTb() { return diemTb; }
    public void setDiemTb(Double diemTb) { this.diemTb = diemTb; }
}
