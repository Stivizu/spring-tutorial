package com.stivizu.tutorial.spring.service;

import com.stivizu.tutorial.spring.model.Student;
import com.stivizu.tutorial.spring.model.entity.StudentEntity;
import com.stivizu.tutorial.spring.model.entity.StudentMapper;
import com.stivizu.tutorial.spring.repository.StudentRepository;
import com.stivizu.tutorial.spring.service.exception.StudentNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.stivizu.tutorial.spring.model.entity.StudentMapper.toDomain;
import static com.stivizu.tutorial.spring.model.entity.StudentMapper.toEntity;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(final long id) {
        final StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There exists not student with id " + id + "!"));
        return toDomain(studentEntity);
    }

    public Page<Student> getStudentsWithAddressInCity(
            final String zipCode,
            final Pageable pageable
    ) {
        final Page<StudentEntity> studentEntity = studentRepository.findAllByAddressesZipAndActiveTrue(zipCode, pageable);
        return studentEntity.map(StudentMapper::toDomain);
    }

    public Page<Student> getStudentsBornInYear(
            final int year,
            final Pageable pageable
    ) {
        final Page<StudentEntity> studentEntity = studentRepository.findAllBornInYearAndActiveTrue(year, pageable);
        return studentEntity.map(StudentMapper::toDomain);
    }

    public Page<Student> getAllStudents(final Pageable pageable) {
        final Page<StudentEntity> studentEntities = studentRepository.findAllByActiveTrue(pageable);
        return studentEntities.map(StudentMapper::toDomain);
    }

    public void addStudent(final Student student) {
        final StudentEntity studentEntity = toEntity(student);
        studentRepository.save(studentEntity);
    }

    public void updateStudent(final long id, final Student student) {
        final boolean studentExists = studentRepository.existsById(id);
        if (!studentExists) {
            throw new StudentNotFoundException(id);
        }
        final StudentEntity studentEntity = toEntity(student);
        studentEntity.setId(id);
        studentRepository.save(studentEntity);
    }

    /*
        Notice how this time we do need to add the @Transactional here, as well as the @Modifying on the Repository query, since we are now using custom queries.
     */
    @Transactional
    public void deleteStudent(final long id) {
        studentRepository.softDeleteById(id);
    }

    @Transactional
    public void deleteAllStudents() {
        studentRepository.softDeleteAll();
    }

}
