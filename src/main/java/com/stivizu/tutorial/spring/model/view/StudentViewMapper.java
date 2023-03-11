package com.stivizu.tutorial.spring.model.view;

import com.stivizu.tutorial.spring.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentViewMapper {

    StudentView toView(Student student);

    Student toDomain(StudentView studentView);

}
