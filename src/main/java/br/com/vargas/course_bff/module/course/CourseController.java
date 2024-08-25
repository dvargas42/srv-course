package br.com.vargas.course_bff.module.course;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vargas.course_bff.module.course.dto.CourseCreateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CourseListRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseListResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CoursePatchResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CourseUpdateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseUpdateResponseDTO;
import br.com.vargas.course_bff.module.course.useCase.CourseCreateUseCase;
import br.com.vargas.course_bff.module.course.useCase.CourseDeleteUseCase;
import br.com.vargas.course_bff.module.course.useCase.CourseGetUseCase;
import br.com.vargas.course_bff.module.course.useCase.CourseListAllUseCase;
import br.com.vargas.course_bff.module.course.useCase.CoursePatchUseCase;
import br.com.vargas.course_bff.module.course.useCase.CourseUpdateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseCreateUseCase courseCreateUseCase;

    @Autowired
    private CourseListAllUseCase courseListAllUseCase;

    @Autowired
    private CourseGetUseCase courseGetUseCase;

    @Autowired
    private CourseUpdateUseCase courseUpdateUseCase;

    @Autowired
    private CourseDeleteUseCase courseDeleteUseCase;

    @Autowired
    private CoursePatchUseCase coursePatchUseCase;
    
    @PostMapping
    public ResponseEntity<CourseCreateResponseDTO> createCourse(
        @Valid @RequestBody CourseCreateRequestDTO request
    ) {
        CourseCreateResponseDTO response = courseCreateUseCase.execute(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CourseListResponseDTO>> listCourses(
        @Valid @ModelAttribute CourseListRequestDTO request
    ) {
        Page<CourseListResponseDTO> response = courseListAllUseCase.execute(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseListResponseDTO> getCourse(@PathVariable UUID id) {
        CourseListResponseDTO response = courseGetUseCase.execute(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseUpdateResponseDTO> updateCourse(
        @PathVariable UUID id, 
        @Valid @RequestBody CourseUpdateRequestDTO request
    ) {
        CourseUpdateResponseDTO response = courseUpdateUseCase.execute(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID id) {         
        courseDeleteUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();        
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CoursePatchResponseDTO> patchCourse(@PathVariable UUID id) {
        CoursePatchResponseDTO response = coursePatchUseCase.execute(id);
        return ResponseEntity.ok().body(response);
    }
}
