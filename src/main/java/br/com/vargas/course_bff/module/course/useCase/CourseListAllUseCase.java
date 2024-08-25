package br.com.vargas.course_bff.module.course.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.module.course.CourseEntity;
import br.com.vargas.course_bff.module.course.CourseRepository;
import br.com.vargas.course_bff.module.course.dto.CourseListRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseListResponseDTO;

@Service
public class CourseListAllUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public Page<CourseListResponseDTO> execute(CourseListRequestDTO request) {

        Page<CourseEntity> coursePageable = null;
        
        if (request.getName() != null || request.getCategory() != null) {
            coursePageable = courseRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            request.getName(), 
            request.getCategory(),
            request);
        } else {
            coursePageable = courseRepository.findAll(request);
        }

        List<CourseListResponseDTO> courseList = coursePageable.getContent()
            .stream().map(course -> {
                return CourseListResponseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .category(course.getCategory())
                    .active(course.getActive())
                    .build(); 
            }).toList();

        return new PageImpl<>(courseList, coursePageable.getPageable(), coursePageable.getTotalElements());
    }
}
