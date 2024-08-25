package br.com.vargas.course_bff.module.course.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseListResponseDTO {

    private UUID id;
    private String name;
    private String category;
    private Boolean active;
}
