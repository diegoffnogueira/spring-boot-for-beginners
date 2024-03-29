package com.diego.spring.service;

import com.diego.spring.dao.StudentDao;
import com.diego.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;
    private final StudentDao studentMongoDbDao;

    @Autowired
    public StudentService(@Qualifier("fakeDao") StudentDao studentDao, @Qualifier("mongoDBDao") StudentDao studentMongoDbDao) {
        this.studentDao = studentDao;
        this.studentMongoDbDao = studentMongoDbDao;
    }

    public int persistNewStudent(UUID studentId, Student student){
        UUID studentUid = studentId == null ? UUID.randomUUID() : studentId;
        student.setId(studentId);
        return studentDao.insertNewStudent(studentUid, student);
    }

    public Student getStudentById(UUID studentId){
        return studentDao.selectStudentById(studentId);
    }

    public List<Student> getAllStudents(){
        return studentDao.selectAllStudents();
    }

    public List<Student> getAllStudentsMongoDb(){
        return studentMongoDbDao.selectAllStudents();
    }

    public int updateStudentById(UUID studentId, Student studentUpdate){
        return studentDao.updateStudentById(studentId, studentUpdate);
    }

    public int deleteStudentById(UUID studentId){
        return studentDao.deleteStudentById(studentId);
    }

}
