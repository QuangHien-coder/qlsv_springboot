package com.hien.qlsv.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String maSv;

    @Column(nullable = false, length = 100)
    private String hoTen;

    @Column(length = 100)
    private String email;

    private LocalDate ngaySinh;

    @Column(length = 20)
    private String lop;

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
