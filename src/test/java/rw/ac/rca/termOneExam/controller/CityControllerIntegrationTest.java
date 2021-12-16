package rw.ac.rca.termOneExam.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.service.CityService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void findById_testSuccess(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/cities/id/101", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findById_testNotFound() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/cities/id/11", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void getAll_testSuccess(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/cities/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void createCity_testSuccess(){
        City city =  new City(1l, "Karongi",20 );
        ResponseEntity<City> response = testRestTemplate.postForEntity("/api/cities/add", city, City.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Karongi", Objects.requireNonNull(response.getBody()).getName());

    }


    @Test
    public void createCity_notFound() {
        CreateCityDTO dto = new CreateCityDTO("Karongi",20);

        ResponseEntity<City> response = this.testRestTemplate.postForEntity("/api/cities/add", dto, City.class);

        assertEquals(201, response.getStatusCodeValue());
//        assertEquals(dto, response.getBody());
    }



}
