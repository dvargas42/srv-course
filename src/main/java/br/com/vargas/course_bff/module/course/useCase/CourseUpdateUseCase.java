package br.com.vargas.course_bff.module.course.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.exception.CourseNotFoundException;
import br.com.vargas.course_bff.module.course.CourseEntity;
import br.com.vargas.course_bff.module.course.CourseRepository;
import br.com.vargas.course_bff.module.course.dto.CourseUpdateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseUpdateResponseDTO;

@Service
public class CourseUpdateUseCase {

    @Autowired
    private CourseRepository courseRepository;
    
    public CourseUpdateResponseDTO execute(UUID id, CourseUpdateRequestDTO request) {

        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException(
                "id", id.toString(), 
                "request", request.toString());
        });

        if (request.getName() != null)
            course.setName(request.getName());
        
        if (request.getCategory() != null)
            course.setCategory(request.getCategory());

        courseRepository.save(course);

        return CourseUpdateResponseDTO.builder()
            .id(course.getId())
            .name(course.getName())
            .category(course.getCategory())
            .active(course.getActive())
            .build();
    }
}
