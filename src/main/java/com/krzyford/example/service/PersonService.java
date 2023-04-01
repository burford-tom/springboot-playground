package com.krzyford.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krzyford.example.entity.Person;
import com.krzyford.example.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public List<Person> read() {
        return personRepository.findAll();
    }
}
