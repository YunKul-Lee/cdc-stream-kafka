package com.jake.cdc.controller;

import com.jake.cdc.entity.Person;
import com.jake.cdc.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonRepository personRepo;

    @GetMapping("/random")
    public ResponseEntity<Person> random() {
        final String randomFirstName = "Jake" + Math.random();
        final String randomLastName = "PP" + Math.random();

        Person savedPerson = personRepo.save(new Person(randomFirstName, randomLastName));

        return ResponseEntity.ok(savedPerson);
    }
}
