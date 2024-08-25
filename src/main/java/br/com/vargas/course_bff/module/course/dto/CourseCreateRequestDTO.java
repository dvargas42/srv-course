package br.com.vargas.course_bff.module.course.dto;

import br.com.vargas.course_bff.enums.EActive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseCreateRequestDTO {
    
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][\\s\\S]*$")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][\\s\\S]*$")
    private String category;

    private EActive active;
}
