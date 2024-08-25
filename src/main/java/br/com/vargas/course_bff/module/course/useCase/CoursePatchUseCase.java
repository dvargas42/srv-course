package br.com.vargas.course_bff.module.course.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.exception.CourseNotFoundException;
import br.com.vargas.course_bff.module.course.CourseEntity;
import br.com.vargas.course_bff.module.course.CourseRepository;
import br.com.vargas.course_bff.module.course.dto.CoursePatchResponseDTO;

@Service
public class CoursePatchUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CoursePatchResponseDTO execute(UUID id) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException("id", id.toString());
        });

        course.setActive(!course.getActive());
        courseRepository.save(course);

        return CoursePatchResponseDTO.builder()
            .id(course.getId())
            .active(course.getActive())
            .build();
    }
}
