package com.app.student.service;

import com.app.student.model.Student;
import com.app.student.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

   public List<Student> listAllStudents(){
       return studentRepo.findAll();
   }

   public Student addStudent(Student student){
      return  studentRepo.save(student);
   }


   public Student getStudentById(Integer id){
       return studentRepo.findById(id).get();
   }

   public void deleteStudentByid(Integer id){
       studentRepo.deleteById(id);
   }
}
