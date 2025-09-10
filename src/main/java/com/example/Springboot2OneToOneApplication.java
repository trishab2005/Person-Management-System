package com.example;

import com.example.entity.AadharCard;
import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot2OneToOneApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    public Springboot2OneToOneApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Springboot2OneToOneApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setName("Ravi");
        person.setAge(25);

        AadharCard aadharCard = new AadharCard();
        aadharCard.setAadharNumber("123456789012");
        aadharCard.setAddress("Delhi");

        person.setAadharCard(aadharCard);
        personRepository.save(person);
        System.out.println("Person saved to the database.");
    }
}