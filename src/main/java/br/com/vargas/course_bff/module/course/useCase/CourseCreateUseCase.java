package br.com.vargas.course_bff.module.course.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.enums.EActive;
import br.com.vargas.course_bff.module.course.CourseEntity;
import br.com.vargas.course_bff.module.course.CourseRepository;
import br.com.vargas.course_bff.module.course.dto.CourseCreateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateResponseDTO;

@Service
public class CourseCreateUseCase {

    @Autowired
    private CourseRepository courseRepository;
    
    public CourseCreateResponseDTO execute(CourseCreateRequestDTO request) {
        CourseEntity course = CourseEntity.builder()
            .name(request.getName())
            .category(request.getCategory())
            .active(request.getActive().getValue())
            .build();
        
        CourseEntity newCourse = courseRepository.save(course);

        return CourseCreateResponseDTO.builder()
            .id(newCourse.getId())
            .name(newCourse.getName())
            .category(newCourse.getCategory())
            .active(EActive.valueOf(newCourse.getActive().toString().toUpperCase()))
            .build();
    }
}
