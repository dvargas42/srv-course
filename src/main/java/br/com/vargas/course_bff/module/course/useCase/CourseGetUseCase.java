package br.com.vargas.course_bff.module.course.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.exception.CourseNotFoundException;
import br.com.vargas.course_bff.module.course.CourseEntity;
import br.com.vargas.course_bff.module.course.CourseRepository;
import br.com.vargas.course_bff.module.course.dto.CourseListResponseDTO;

@Service
public class CourseGetUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseListResponseDTO execute(UUID id) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException("id", id.toString());
        });

        return CourseListResponseDTO.builder()
            .id(course.getId())
            .name(course.getName())
            .category(course.getCategory())
            .active(course.getActive())
            .build();
    }
}
