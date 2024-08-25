package br.com.vargas.course_bff.module.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import br.com.vargas.course_bff.enums.EActive;
import br.com.vargas.course_bff.module.course.dto.CourseCreateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateTestRequestDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class CourseControllerCreateTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    private final String NAME = "NAME_TEST";
    private final String CATEGORY = "CATEGORY_TEST";
    private final EActive ACTIVE = EActive.TRUE;

    @Test
    void shouldToBeAbleToCreateCourse() {
        CourseCreateRequestDTO bodyResquest = new CourseCreateRequestDTO(
            NAME, CATEGORY,ACTIVE);

        ResponseEntity<CourseCreateResponseDTO> response = restTemplate.postForEntity(
            "/cursos", 
            bodyResquest, 
            CourseCreateResponseDTO.class
        );
        
        try {
            CourseCreateResponseDTO course = response.getBody();

            assertEquals(200, response.getStatusCode().value());
            assertThat(course.getId()).isInstanceOf(UUID.class);
            assertEquals(NAME, course.getName());
            assertEquals(CATEGORY, course.getCategory());
            assertEquals(ACTIVE, course.getActive());
        } catch (Exception e) {
            fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
        "\" NAME_TEST\",    CATEGORY_TEST,          TRUE",
        "1NAME_TEST,        CATEGORY_TEST,          TRUE",
        "@NAME_TEST,        CATEGORY_TEST,          TRUE",
        "\" \",             CATEGORY_TEST,          TRUE",
        "\"\",              CATEGORY_TEST,          TRUE",
        ",                  CATEGORY_TEST,          TRUE",
        "NAME_TEST,         \" CATEGORY_TEST\",     TRUE",
        "NAME_TEST,         1CATEGORY_TEST,         TRUE",
        "NAME_TEST,         @CATEGORY_TEST,         TRUE",
        "NAME_TEST,         \" \",                  TRUE",
        "NAME_TEST,         \"\",                   TRUE",
        "NAME_TEST,         ,                       TRUE",
        "NAME_TEST,         CATEGORY_TEST,          true",
        "NAME_TEST,         CATEGORY_TEST,          false",
        "NAME_TEST,         CATEGORY_TEST,          TEST",
        "NAME_TEST,         CATEGORY_TEST,          \" \"",
        "NAME_TEST,         CATEGORY_TEST,          \"\"",
        "NAME_TEST,         CATEGORY_TEST,              ",
    })
    void shouldToBeNotAbleToCreateCourseWhenInvalidValues(
        String name, String category, String active
    ) {
        CourseCreateTestRequestDTO bodyResquest = new CourseCreateTestRequestDTO(
            name, category, active);
        ResponseEntity<CourseCreateResponseDTO> response = restTemplate.postForEntity(
            "/cursos", 
            bodyResquest, 
            CourseCreateResponseDTO.class
        );

        assertEquals(400, response.getStatusCode().value());
    }
}
