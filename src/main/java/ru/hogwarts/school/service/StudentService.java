package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.student.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student getStudentInfo(Long studentsId){
        return studentRepository.findById(studentsId).get();
    }

    public Collection<Student> getAllStudentsInfo(){
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Student editStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentsId){
        studentRepository.deleteById(studentsId);
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
