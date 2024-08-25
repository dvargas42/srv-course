package br.com.vargas.course_bff.module.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vargas.course_bff.enums.EActive;
import br.com.vargas.course_bff.module.course.dto.CourseCreateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseListResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CoursePageResponseDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CourseControllerFindByNameOrCategoryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String COURSE01_NAME = "NAME01_TEST";
    private final String COURSE02_NAME = "NAME02_TEST";
    private final String COURSE03_NAME = "NAME03_TEST";

    private final String COURSE_CATEGORY01 = "CATEGORY01_TEST";
    private final String COURSE_CATEGORY02 = "CATEGORY02_TEST";

    private final EActive COURSE01_ACTIVE = EActive.TRUE;
    private final EActive COURSE02_ACTIVE = EActive.FALSE;

    @Test
    void shouldToBeAbleToFindCoursesByName() throws JsonProcessingException {

        List<CourseCreateRequestDTO> courses = List.of(
            new CourseCreateRequestDTO(COURSE01_NAME, COURSE_CATEGORY01, COURSE01_ACTIVE),
            new CourseCreateRequestDTO(COURSE02_NAME, COURSE_CATEGORY02, COURSE02_ACTIVE),
            new CourseCreateRequestDTO(COURSE03_NAME, COURSE_CATEGORY02, COURSE01_ACTIVE)
        );

        for(CourseCreateRequestDTO course: courses) {
            restTemplate.postForEntity("/cursos", course, String.class);
        }

        ResponseEntity<String> response = restTemplate.getForEntity("/cursos?name=" + COURSE01_NAME, String.class);

        String responseBody = response.getBody();
        CoursePageResponseDTO coursePage = objectMapper.readValue(
            responseBody,
            CoursePageResponseDTO.class
        );

        List<CourseListResponseDTO> courseResponse = coursePage.getContent();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(courseResponse);
        assertEquals(1, courseResponse.size());
        assertEquals(courses.get(0).getName(), courseResponse.get(0).getName());

    }

    @Test
    void shouldToBeAbleToFindCoursesByCategory() throws JsonProcessingException {

        List<CourseCreateRequestDTO> courses = List.of(
            new CourseCreateRequestDTO(COURSE01_NAME, COURSE_CATEGORY01, COURSE01_ACTIVE),
            new CourseCreateRequestDTO(COURSE02_NAME, COURSE_CATEGORY02, COURSE02_ACTIVE),
            new CourseCreateRequestDTO(COURSE03_NAME, COURSE_CATEGORY02, COURSE01_ACTIVE)
        );

        for(CourseCreateRequestDTO course: courses) {
            restTemplate.postForEntity("/cursos", course, String.class);
        }

        ResponseEntity<String> response = restTemplate.getForEntity("/cursos?category=" + COURSE_CATEGORY02, String.class);

        String responseBody = response.getBody();
        CoursePageResponseDTO coursePage = objectMapper.readValue(
            responseBody,
            CoursePageResponseDTO.class
        );

        List<CourseListResponseDTO> courseResponse = coursePage.getContent();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(courseResponse);
        assertEquals(2, courseResponse.size());
        assertEquals(courses.get(2).getCategory(), courseResponse.get(0).getCategory());
    }

}