package com.example.OpenBootcampCrudApiRest.Controller;

import com.example.OpenBootcampCrudApiRest.Entity.LaptopEntity;
import com.example.OpenBootcampCrudApiRest.OpenBootcampCrudApiRestApplication;
import com.example.OpenBootcampCrudApiRest.Repository.LaptopRepository;
import com.sun.istack.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.service.Header;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port ;



    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @BeforeAll
    static void setUpAll(){

    }

    @DisplayName("Testeando findAll")
    @Test
    void findAll() {
        ResponseEntity<LaptopEntity[]> response =  testRestTemplate.getForEntity("/app/laptops",
                LaptopEntity[].class);
        List<LaptopEntity> list = Arrays.asList(response.getBody());

        //Probando el tipo de respuesta debido a que hay 2 Laptop creadas con el metodo Create
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //probamos que los datos de la peticion devulta sean correctos

        assertEquals(2, list.size()); //deberian haber 2 objetos
        assertEquals(1, list.get(0).getId());//el indice del primero deberia ser 1
        assertEquals(2, list.get(1).getId());//el indice del segundo seria 2


    }

    @Test
    void findById() {
        ResponseEntity<LaptopEntity> response =  testRestTemplate.getForEntity("/app/laptop/1",
                LaptopEntity.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Galaxy S5", response.getBody().getModel());

        //Enviamos otra peticion y validamos que uno de los campos de la respuesta sean correctos

        response =  testRestTemplate.getForEntity("/app/laptop/2",
                LaptopEntity.class);

        assertEquals("01/03/1999", response.getBody().getDate());
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ResponseEntity<LaptopEntity> responseOne;
        ResponseEntity<LaptopEntity> responseTwo;
        ResponseEntity<LaptopEntity> responseThree;

        String route = "/app/laptop";

        String json1 = """
                {
                    "id": 5,
                    "model": "Galaxy S5",
                    "date": "01/03/1996",
                    "brand": "Samsung"
                }""";
        String json2 = """
                {
                    "model": "Iphone 11",
                    "date": "01/03/1999",
                    "brand": "Iphone"
                }""";

        // Aqui diligenciamos una laptop que tenga un id que ya deberia existir en BD.
        String json3 = """
                {
                    "id": 1,
                    "model": "Motorola X",
                    "date": "01/03/2014",
                    "brand": "Motorola"
                }""";


        HttpEntity<String> request1 = new HttpEntity<>(json1, headers);
        HttpEntity<String> request2 = new HttpEntity<>(json2, headers);
        HttpEntity<String> request3 = new HttpEntity<>(json3, headers);

        responseOne = testRestTemplate.exchange(route, HttpMethod.POST, request1, LaptopEntity.class);
        responseTwo = testRestTemplate.exchange(route, HttpMethod.POST, request2, LaptopEntity.class);
        //Esta peticion deberia retornar una BAD_REQUEST
        responseThree = testRestTemplate.exchange(route, HttpMethod.POST, request3, LaptopEntity.class);

        assertEquals(HttpStatus.OK, responseOne.getStatusCode());
        assertEquals(1, responseOne.getBody().getId());

        assertEquals(HttpStatus.OK, responseTwo.getStatusCode());
        assertEquals(2, responseTwo.getBody().getId());

        assertEquals(HttpStatus.BAD_REQUEST, responseThree.getStatusCode());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void isNull() {
    }
}