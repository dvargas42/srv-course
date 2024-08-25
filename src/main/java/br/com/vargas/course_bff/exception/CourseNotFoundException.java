package br.com.vargas.course_bff.exception;

public class CourseNotFoundException extends CustomException {

    public CourseNotFoundException(String... searchParamsMap) {
        super("Course not found", searchParamsMap);
    }
    
}
