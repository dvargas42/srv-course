package br.com.vargas.course_bff.module.course.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseListRequestDTO implements Pageable{

    @Min(0)
    @Builder.Default
    private int page = 0;
        
    @Min(0)
    @Builder.Default
    private int size = 100;

    @Builder.Default
    private Sort sort = Sort.by(Sort.Order.asc("name"));

    private String name;

    private String category;

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return page * size;
    }

    @Override
    public Pageable next() {
        return new CourseListRequestDTO(page + 1, size, sort, name, category);
    }

    @Override
    public Pageable previousOrFirst() {
        return new CourseListRequestDTO(Math.max(page - 1, 0), size, sort, name, category);
    }

    @Override
    public Pageable first() {
        return new CourseListRequestDTO(0, size, sort, name, category);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new CourseListRequestDTO(pageNumber, size, sort, name, category);
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }
}
