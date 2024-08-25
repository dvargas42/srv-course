package br.com.vargas.course_bff.module.course;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    Page<CourseEntity> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category, Pageable request);
    
}
        