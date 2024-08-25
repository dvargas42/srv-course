package br.com.vargas.course_bff.module.course.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vargas.course_bff.exception.CourseNotFoundException;
import br.com.vargas.course_bff.module.course.CourseRepository;

@Service
public class CourseDeleteUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id) {
        courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFoundException("id", id.toString());
        });

        courseRepository.deleteById(id);
    }
}
