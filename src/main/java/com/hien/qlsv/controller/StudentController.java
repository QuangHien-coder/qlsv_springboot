package com.hien.qlsv.controller;

import com.hien.qlsv.dto.StudentDto;
import com.hien.qlsv.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /students?keyword=... -> danh sach
    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("students", studentService.findAll(keyword));   // "do du lieu" sang view
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // GET /students/new -> form them
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "student-form";
    }

    // GET /students/5/edit -> form sua, da dien san du lieu
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("studentDto", studentService.findById(id));
        return "student-form";
    }

    // POST /students/save -> dung chung cho them va sua
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("studentDto") StudentDto dto, BindingResult result,
                       RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "student-form";
        }
        try {
            if (dto.getId() == null) {
                studentService.create(dto);
                ra.addFlashAttribute("msg", "Đã thêm sinh viên " + dto.getHoTen());
            } else {
                studentService.update(dto.getId(), dto);
                ra.addFlashAttribute("msg", "Đã cập nhật sinh viên " + dto.getHoTen());
            }
            // Redirect sau POST: F5 khong bi gui form lan 2
            return "redirect:/students";
        } catch (IllegalArgumentException e) {
            result.rejectValue("maSv", "duplicate", e.getMessage());   // hien loi ngay o Ma SV
            return "student-form";
        }
    }

    // POST /students/5/delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        studentService.delete(id);
        ra.addFlashAttribute("msg", "Đã xóa sinh viên");
        return "redirect:/students";
    }
}
