package com.hien.qlsv.service;

import com.hien.qlsv.dto.StudentDto;
import com.hien.qlsv.entity.Student;
import com.hien.qlsv.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // READ: lay danh sach (co tim kiem), tra ve List<DTO> cho Controller
    public List<StudentDto> findAll(String keyword) {
        List<Student> list = (keyword == null || keyword.isBlank())
                ? studentRepository.findAll()
                : studentRepository.findByHoTenContainingIgnoreCaseOrMaSvContainingIgnoreCase(keyword, keyword);
        return list.stream().map(this::toDto).toList();
    }

    public StudentDto findById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên id = " + id));
        return toDto(s);
    }

    // CREATE
    public void create(StudentDto dto) {
        if (studentRepository.existsByMaSv(dto.getMaSv())) {
            throw new IllegalArgumentException("Mã SV đã tồn tại");
        }
        Student s = new Student();
        copy(dto, s);
        studentRepository.save(s);   // entity chua co id -> INSERT
    }

    // UPDATE
    public void update(Long id, StudentDto dto) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên id = " + id));
        if (studentRepository.existsByMaSvAndIdNot(dto.getMaSv(), id)) {
            throw new IllegalArgumentException("Mã SV đã tồn tại");
        }
        copy(dto, s);
        studentRepository.save(s);   // entity da co id -> UPDATE
    }

    // DELETE
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    // ---- Chuyen doi Entity <-> DTO ----
    private StudentDto toDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setId(s.getId());
        dto.setMaSv(s.getMaSv());
        dto.setHoTen(s.getHoTen());
        dto.setEmail(s.getEmail());
        dto.setNgaySinh(s.getNgaySinh());
        dto.setLop(s.getLop());
        dto.setDiemTb(s.getDiemTb());
        return dto;
    }

    // Khong copy id: id do DB quyet dinh, khong tin id tu form gui len
    private void copy(StudentDto dto, Student s) {
        s.setMaSv(dto.getMaSv().trim());
        s.setHoTen(dto.getHoTen().trim());
        s.setEmail(dto.getEmail());
        s.setNgaySinh(dto.getNgaySinh());
        s.setLop(dto.getLop());
        s.setDiemTb(dto.getDiemTb());
    }
}
