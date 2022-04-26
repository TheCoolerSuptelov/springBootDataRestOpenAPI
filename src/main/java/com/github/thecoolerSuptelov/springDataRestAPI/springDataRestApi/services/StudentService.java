package com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.DTO.StudentDTO;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.entities.Student;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.repositories.CourseRepository;
import com.github.thecoolerSuptelov.springDataRestAPI.springDataRestApi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
  private StudentRepository studentRepository;
  private ObjectMapper objectMapperJson;
  private CourseRepository courseRepository;

  public CourseRepository getCourseRepository() {
    return courseRepository;
  }
  @Autowired
  public void setCourseRepository(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  /**
   * Empty constructor
   */
  public StudentService() {
    // default no args empty constructor
  }

  public StudentRepository getStudentRepository() {
    return studentRepository;
  }

  @Autowired
  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
    objectMapperJson = new ObjectMapper();
  }

  public Student buildStudentByName(String name) {
    Student student = new Student(name);
    studentRepository.save(student);
    return student;
  }

  public Student buildStudentByFullStructure(Student student) {
    studentRepository.save(student);
    return student;
  }
  /**
   * Basic serialization function using Jackson.
   *
   * @param student entity class of this app.
   * @return JSON representation of student
   */
  public String serializeStudentToJson(Student student) {
    try {
      return objectMapperJson.writeValueAsString(student);
    } catch (JsonProcessingException e) {
      return "{error:\"cannot convert object to json\"}";
    }
  }

  public List<Student> findAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> findByName(String studentName) {
    return studentRepository.findByNameEqualsIgnoreCase(studentName);
  }
  public Student findByNameJPQL(String studentName){
    return studentRepository.findByName(studentName);
  }

  public Student buildFromDTO(StudentDTO studentDTO){
    Student newStudent = buildStudentByName(studentDTO.getName());
    newStudent.setDateOfBirthday(studentDTO.getDateOfBirthday());
    newStudent.setCourse(courseRepository.findByTitleEqualsIgnoreCase(studentDTO.getCourse()));
    return newStudent;
  }
  public String deleteStudentByName(String name){
    return String.valueOf(studentRepository.deleteByNameEqualsIgnoreCase(name));
  }
  public StudentDTO buildDTObyObject(Student student){
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setName(student.getName());
    studentDTO.setDateOfBirthday(student.getDateOfBirthday());
    return studentDTO;
  }
  public List<StudentDTO> getStudentPagination(int pageNo,int pageSize){
    var pageable = PageRequest.of(pageNo-1, pageSize);
    return studentRepository.findAll(pageable).getContent().stream()
        .map(this::buildDTObyObject).
        collect(Collectors.toList());
  }
  public List<Student> getStudentsOrderedByName(){
    return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
  }
  public void updateFirstName(){
    studentRepository.updateFirstName("Undefined");
  }
}
