package com.project.school.DTO;

import com.project.school.entities.Nota;
import com.project.school.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentNotaDTO {

    private String name;
    private String response;
    private int age;
    private Nota nota;
}
