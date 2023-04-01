package com.krzyford.example.model;

import java.util.List;

import lombok.Data;

@Data
public class ContactList {
    List<Person> persons;
}
