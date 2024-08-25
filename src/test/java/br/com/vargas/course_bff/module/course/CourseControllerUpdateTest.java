package br.com.vargas.course_bff.module.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vargas.course_bff.module.course.dto.CourseCreateResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateTestRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseListResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CourseUpdateRequestDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CourseControllerUpdateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String COURSE01_NAME = "NAME01_TEST";

    private final String COURSE01_CATEGORY = "CATEGORY01_TEST";

    private final String COURSE01_ACTIVE = "FALSE";

    @ParameterizedTest
    @CsvSource({
        "NAME_TEST,     CATEGORY_TEST",
        ",              CATEGORY_TEST",
        "NAME_TEST,",
    })
    void shouldToBeAbleToUpdateCourse(String name, String category) throws JsonProcessingException {

        CourseCreateTestRequestDTO course = new CourseCreateTestRequestDTO(COURSE01_NAME, COURSE01_CATEGORY, COURSE01_ACTIVE);
        ResponseEntity<String> response = restTemplate.postForEntity("/cursos", course, String.class);

        String responseBody = response.getBody();

        CourseCreateResponseDTO courseResponse = objectMapper.readValue(
            responseBody,
            CourseCreateResponseDTO.class
        );

        CourseUpdateRequestDTO courseUpdate = new CourseUpdateRequestDTO(name, category);

        restTemplate.put("/cursos/" + courseResponse.getId(), courseUpdate);

       ResponseEntity<String> responseGet = restTemplate.getForEntity(
            "/cursos/" + courseResponse.getId(), 
            String.class
        );

        responseBody = responseGet.getBody();
        CourseListResponseDTO courseResponseUpdate = objectMapper.readValue(
            responseBody,
            CourseListResponseDTO.class
        );

        name = name != null ? name : COURSE01_NAME;
        category = category != null ? category : COURSE01_CATEGORY;

        assertEquals(200, response.getStatusCode().value());
        assertEquals(name, courseResponseUpdate.getName());
        assertEquals(category, courseResponseUpdate.getCategory());
        assertEquals(course.getActive().toString(), courseResponseUpdate.getActive().toString().toUpperCase());
    }
}