package br.com.vargas.course_bff.module.course.dto;

import java.util.UUID;

import br.com.vargas.course_bff.enums.EActive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateResponseDTO {
    
    private UUID id;
    private String name;
    private String category;
    private EActive active;
}
