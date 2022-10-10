package com.app.student.controller;

import com.app.student.model.Student;
import com.app.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<Student>listAllStudents(){
       return studentService.listAllStudents();
    }
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        try {
            Student student=studentService.getStudentById(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("students/add")
    public String add(@RequestBody Student student){
        studentService.addStudent(student);
        return "Student added";
    }

    @PutMapping("/students/put/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student,@PathVariable Integer id){
        try {
            Student existStudent= studentService.getStudentById(id);
            if(existStudent!=null)
            {
                if(student.getAge()!=null)
                {
                    existStudent.setAge(student.getAge());
                }
                if(student.getGender()!=null)
                {
                    existStudent.setGender(student.getGender());
                }
                if(student.getFirstName()!=null)
                {
                    existStudent.setFirstName(student.getFirstName());
                }
                if(student.getLastName()!=null)
                {
                    existStudent.setLastName(student.getLastName());
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            studentService.addStudent(existStudent);
            return new ResponseEntity<Student>(existStudent,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id){
         studentService.deleteStudentByid(id);
         return "student deleted successfully";
    }
}
