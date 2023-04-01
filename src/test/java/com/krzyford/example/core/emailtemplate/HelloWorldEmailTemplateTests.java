package com.krzyford.example.core.emailtemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.krzyford.example.model.Person;
import com.krzyford.example.model.ContactList;
import com.krzyford.example.model.Email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class HelloWorldEmailTemplateTests {

    @Autowired
    HelloWorldEmailTemplate helloWorldEmailTemplate;

    @Test
    void givenASinglePersonThenInsertName() {
        Email valueUnderTest = null;
        ContactList validContactList = new ContactList();
        Person validPerson = new Person();

        validPerson.setFirstName("Steve");
        validPerson.setMiddleName("Grant");
        validPerson.setLastName("Rogers");

        validContactList.setPersons(List.of(validPerson));

        valueUnderTest = helloWorldEmailTemplate.fill(validContactList);

        log.info(valueUnderTest.getBody());
        assertTrue(valueUnderTest.getBody().contains("Steve"));
        assertTrue(valueUnderTest.getBody().contains("Grant"));
        assertTrue(valueUnderTest.getBody().contains("Rogers"));
    }

    @Test
    void givenMoreThanTenPersonsThenInsertAttachment() {
        Email valueUnderTest = null;
        ContactList validContactList = new ContactList();
        List<Person> validPersons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person validPerson = new Person();
            validPerson.setFirstName("Steve");
            validPerson.setMiddleName("Grant");
            validPerson.setLastName("Rogers");
            validPersons.add(validPerson);
        }

        validContactList.setPersons(validPersons);

        valueUnderTest = helloWorldEmailTemplate.fill(validContactList);

        log.info(valueUnderTest.getBody());
        assertTrue(valueUnderTest.getBody().contains("See attached list of Persons"));
    }
}
