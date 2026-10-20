package com.hien.qlsv.repository;

import com.hien.qlsv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByMaSv(String maSv);

    // Dung khi sua: co SV KHAC (id khac) dang dung ma nay khong?
    boolean existsByMaSvAndIdNot(String maSv, Long id);

    // Tim kiem: ... WHERE LOWER(ho_ten) LIKE %?% OR LOWER(ma_sv) LIKE %?%
    List<Student> findByHoTenContainingIgnoreCaseOrMaSvContainingIgnoreCase(String hoTen, String maSv);
}
