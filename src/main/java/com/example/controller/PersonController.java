package com.example.controller;
import com.example.entity.Person;
import com.example.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor

public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person),
                HttpStatus.CREATED);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok(person);
    }
}