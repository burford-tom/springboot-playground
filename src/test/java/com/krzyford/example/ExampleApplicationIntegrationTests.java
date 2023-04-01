package com.krzyford.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.krzyford.example.entity.Person;

@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ExampleApplicationIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void restTest() {
        List<Object> valueUnderTest = null;

        Person saveResult = this.restTemplate.postForEntity("http://localhost:" + "8090" + "/persons", 
            new Person("Steve", "Rogers"), 
            Person.class)
            .getBody();

        Person[] rawResult = this.restTemplate.getForObject("http://localhost:" + "8090" + "/persons",
            Person[].class);

        valueUnderTest = Arrays.asList(rawResult);

        assertNotNull(valueUnderTest);
        assertEquals(1, valueUnderTest.size());
        assertEquals(saveResult, valueUnderTest.get(0));
    }
}
