package com.krzyford.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krzyford.example.entity.Person;

public interface PersonRepository extends JpaRepository<Person,UUID>  {

}
