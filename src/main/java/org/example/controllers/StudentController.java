package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.StudentEnt;
import org.example.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final AddressService addressService;
    private final HobbyService hobbyService;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentEnt> get(@PathVariable Integer id) {
        try {
            StudentEnt studentEnt = studentService.get(id);
            studentEnt.setAddress(addressService.get(id));
            studentEnt.setHobbyEnts(hobbyService.get(id));
            studentEnt.setCourseEnts(courseService.get(id));
            return ResponseEntity.ok(studentEnt);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestParam(required = false, defaultValue = "") String nameStudent,
                             @RequestParam(required = false) Integer facultyId,
                             @RequestParam(required = false, defaultValue = "") String nameCourse,
                             @RequestParam(required = false) String teacher,
                             @RequestParam(required = false, defaultValue = "") String nameAddress,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false, defaultValue = "") String title,
                             @RequestParam(required = false, defaultValue = "0") int studentId,
                             @RequestParam(required = false, defaultValue = "0") int courseId) {
        try {

            if (!nameStudent.isEmpty()) {
                studentService.create(nameStudent, facultyId);
            }
            if (!nameCourse.isEmpty()) {
                courseService.create(nameCourse, teacher);
            }
            if (!nameAddress.isEmpty()) {
                addressService.create(nameAddress, phone, studentId);
            }
            if (!title.isEmpty()) {
                hobbyService.create(title, studentId);
            }
            if (studentId != 0 && courseId != 0) {
                studentCourseService.create(studentId, courseId);
            }
            var n = 1;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;

    }


    @PutMapping("/update")
    public HttpStatus update(@RequestParam String table,
                             @RequestParam String param,
                             @RequestParam String newName,
                             @RequestParam int id) {
        try {
            studentService.updateAnyTable(table, id, param, newName);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestParam(required = false) String table,
                       @RequestParam(required = false) Integer id,
                       @RequestParam(required = false, defaultValue = "0") int studentId,
                       @RequestParam(required = false, defaultValue = "0") int courseId) {
        try {
            if (studentId != 0 && courseId != 0) {
                studentCourseService.delete(studentId, courseId);
            } else {
            studentService.deleteAnyTable(table, id);}
        } catch (SQLException e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
