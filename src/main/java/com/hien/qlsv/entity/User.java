package com.hien.qlsv.entity;

import jakarta.persistence.*;

// ENTITY = 1 class <-> 1 bang trong DB. Moi field <-> 1 cot.
@Entity
@Table(name = "users")
public class User {

    @Id                                                   // khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // tu tang
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;   // luu chuoi da ma hoa BCrypt, KHONG luu mat khau goc

    @Column(nullable = false, length = 100)
    private String hoTen;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
}
