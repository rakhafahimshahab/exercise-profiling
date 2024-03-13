package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        List<StudentCourse> allStudentCourses = studentCourseRepository.findAll();

        List<StudentCourse> studentCourses = new ArrayList<>();
        for (StudentCourse sc : allStudentCourses) {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(sc.getStudent());
            studentCourse.setCourse(sc.getCourse());
            studentCourses.add(studentCourse);
        }

        return studentCourses;
    }

    public Optional<Student> findStudentWithHighestGpa() {
        return studentRepository.findAll().stream()
                .max(Comparator.comparingDouble(Student::getGpa));
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();
        StringBuilder resultBuilder = new StringBuilder();
        for (Student student : students) {
            resultBuilder.append(student.getName()).append(", ");
        }
        if (resultBuilder.length() > 0) {
            resultBuilder.setLength(resultBuilder.length() - 2);
        }
        return resultBuilder.toString();
    }
}

