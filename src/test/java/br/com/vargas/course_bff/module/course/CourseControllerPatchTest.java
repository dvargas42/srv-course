package br.com.vargas.course_bff.module.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vargas.course_bff.enums.EActive;
import br.com.vargas.course_bff.module.course.dto.CourseCreateRequestDTO;
import br.com.vargas.course_bff.module.course.dto.CourseCreateResponseDTO;
import br.com.vargas.course_bff.module.course.dto.CoursePatchResponseDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CourseControllerPatchTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String COURSE01_NAME = "NAME01_TEST";

    private final String COURSE_CATEGORY01 = "CATEGORY01_TEST";

    private final EActive COURSE01_ACTIVE = EActive.FALSE;

    @Test
    void shouldToBeAbleToPatchCourse() throws JsonProcessingException {

        CourseCreateRequestDTO course = new CourseCreateRequestDTO(COURSE01_NAME, COURSE_CATEGORY01, COURSE01_ACTIVE);
        ResponseEntity<String> response = restTemplate.postForEntity("/cursos", course, String.class);

        String responseBody = response.getBody();
        CourseCreateResponseDTO courseResponse = objectMapper.readValue(
            responseBody,
            CourseCreateResponseDTO.class
        );

        var chosenCourseId = courseResponse.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);


        ResponseEntity<String> responsePatch = restTemplate.exchange(
            "/cursos/" + chosenCourseId + "/active", 
            HttpMethod.PATCH,
            requestEntity,  
            String.class
        );

        CoursePatchResponseDTO courseResponsePatch = objectMapper.readValue(
            responsePatch.getBody(),
            CoursePatchResponseDTO.class
        );

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TRUE", courseResponsePatch.getActive().toString().toUpperCase());
    }
}