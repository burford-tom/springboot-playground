package com.krzyford.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.krzyford.example.entity.Person;

@DataJpaTest
public class PersonRepositoryTests {
    @Autowired
    PersonRepository classUnderTest;

    @Test
    void givenANewPerson_ThenANewGuidShouldBeCreated() {
        Person validPerson = new Person("Tom", "Burford");
        Person valueUnderTest;
        UUID guid = classUnderTest.save(validPerson).getGuid();

        valueUnderTest = classUnderTest.getReferenceById(guid);

        assertNotNull(valueUnderTest);
        assertEquals(guid, valueUnderTest.getGuid());
    }
}
