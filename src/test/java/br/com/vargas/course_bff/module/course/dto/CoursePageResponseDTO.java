package br.com.vargas.course_bff.module.course.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoursePageResponseDTO {
    
    @JsonProperty("content")
    private List<CourseListResponseDTO> content;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

    @JsonProperty("size")
    private int size;

    @JsonProperty("number")
    private int number;

    // Getters e Setters
    public List<CourseListResponseDTO> getContent() {
        return content;
    }

    public void setContent(List<CourseListResponseDTO> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}