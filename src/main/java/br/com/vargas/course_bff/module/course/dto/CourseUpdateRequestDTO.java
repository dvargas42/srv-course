package br.com.vargas.course_bff.module.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseUpdateRequestDTO {

    private String name;
    
    private String category;
}
