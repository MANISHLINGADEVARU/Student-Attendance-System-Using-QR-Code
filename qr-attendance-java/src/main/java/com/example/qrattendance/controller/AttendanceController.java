package com.example.qrattendance.controller;

import com.example.qrattendance.model.AttendanceRecord;
import com.example.qrattendance.model.AttendanceSession;
import com.example.qrattendance.model.Student;
import com.example.qrattendance.repository.AttendanceRecordRepository;
import com.example.qrattendance.repository.AttendanceSessionRepository;
import com.example.qrattendance.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AttendanceController {

    private final AttendanceSessionRepository sessionRepository;
    private final AttendanceRecordRepository recordRepository;
    private final StudentRepository studentRepository;

    public AttendanceController(AttendanceSessionRepository sessionRepository,
                                AttendanceRecordRepository recordRepository,
                                StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.recordRepository = recordRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sessions", sessionRepository.findAll());
        return "index";
    }

    @PostMapping("/session/create")
    public String createSession(@RequestParam String subject) {
        AttendanceSession session = new AttendanceSession();
        session.setSubject(subject);
        sessionRepository.save(session);
        return "redirect:/";
    }

    @GetMapping("/session/{id}")
    public String viewSession(@PathVariable Long id, Model model) {
        AttendanceSession session = sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid session Id:" + id));
        List<AttendanceRecord> records = recordRepository.findBySession(session);
        model.addAttribute("attendanceSession", session);
        model.addAttribute("records", records);
        return "session-detail";
    }

    @GetMapping("/mark-attendance/{sessionId}")
    public String markAttendancePage(@PathVariable Long sessionId, Model model) {
        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid session Id:" + sessionId));
        if (!session.isActive()) {
            return "session-closed";
        }
        model.addAttribute("attendanceSession", session);
        return "mark-attendance";
    }

    @PostMapping("/mark-attendance/{sessionId}")
    public String submitAttendance(@PathVariable Long sessionId, 
                                   @RequestParam String rollNumber,
                                   RedirectAttributes redirectAttributes) {
        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid session Id:" + sessionId));
        
        if (!session.isActive()) {
            redirectAttributes.addFlashAttribute("error", "Session is closed!");
            return "redirect:/mark-attendance/" + sessionId;
        }

        Optional<Student> studentOpt = studentRepository.findByRollNumber(rollNumber);
        if (studentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Student not found with Roll Number: " + rollNumber);
            return "redirect:/mark-attendance/" + sessionId;
        }

        Student student = studentOpt.get();
        if (recordRepository.existsByStudentAndSession(student, session)) {
            redirectAttributes.addFlashAttribute("error", "Attendance already marked for this session!");
            return "redirect:/mark-attendance/" + sessionId;
        }

        AttendanceRecord record = new AttendanceRecord();
        record.setSession(session);
        record.setStudent(student);
        recordRepository.save(record);

        redirectAttributes.addFlashAttribute("success", "Attendance marked successfully!");
        return "redirect:/mark-attendance/" + sessionId;
    }
}
