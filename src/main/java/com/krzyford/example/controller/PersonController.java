package com.krzyford.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krzyford.example.entity.Person;
import com.krzyford.example.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    List<Person> all() {
        return personService.read();
    }

    @PostMapping
    Person newPerson(@RequestBody Person person) {
        return personService.create(person);
    }
}
