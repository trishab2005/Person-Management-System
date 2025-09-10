package com.example.repository;

import com.example.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Test
    void testSaveAndFindPerson() {
        AadharCard aadhar = new AadharCard(null, "123456789876", "Chennai");
        Person person = new Person(null, "Krishna", 23, aadhar);
        Person saved = personRepository.save(person);

        Optional<Person> found = personRepository.findByAadharNumber("123456789876");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Krishna");
    }
}