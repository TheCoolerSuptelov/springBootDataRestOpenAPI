package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.controllers;

import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.DTO.StudentDTO;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities.Student;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/*
 */
// D:\java\lrn\!1 Build REST API With Spring Boot & Spring Data JPA\[TutsNode.com] - Build REST API With Spring Boot & Spring Data JPA\10. Java Persistence Query Language (JPQL)
@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  public StudentService studentService;
  Logger logger = LoggerFactory.getLogger(getClass());
  @Value("${app.name: Default Spring REST API}")
  private String applicationName;

  @GetMapping("/")
  public List<Student> getAllStudents() {
    logger.info(Arrays.toString(Thread.currentThread().getStackTrace()).replace(',', '\n'));
    return studentService.findAllStudents();
  }

  @GetMapping("/pagination")
  public List<StudentDTO> getAllStudentWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
    return studentService.getStudentPagination(pageNo, pageSize);
  }

  @GetMapping("/{studentName}")
  public String getBaseUrl(@PathVariable String studentName) {
    Optional<Student> studentOptional = studentService.findByName(studentName);
    if (studentOptional.isPresent()) {
      return studentService.serializeStudentToJson(studentOptional.get());
    }
    return "No such student. In application:\"" + applicationName + "\"";
  }

  @GetMapping("/JPQL/{studentName}")
  public String getJPQLStudent(@PathVariable String studentName) {
    Student student = studentService.findByNameJPQL(studentName);
    return studentService.serializeStudentToJson(student);
  }

  @GetMapping("/JPQL/setUndefined")
  public List<StudentDTO> getJPQLStudent() {
    studentService.updateFirstName();
    return getAllStudentWithPagination(1, 100);
  }

  @PostMapping("/{studentName}")
  public Student createStudentByName(@PathVariable String studentName) {
    return studentService.buildStudentByName(studentName);
  }

  @PostMapping("/")
  public Student createStudentByFullStructure(@Valid @RequestBody StudentDTO studentDto) {
    return studentService.buildFromDTO(studentDto);
  }

  @DeleteMapping("/{name}")
  public String deleteByName(@PathVariable String name) {
    return studentService.deleteStudentByName(name);
  }
}
