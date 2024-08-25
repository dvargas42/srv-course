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
public class CoursePatchResponseDTO {

    private UUID id;

    private Boolean active;
}
