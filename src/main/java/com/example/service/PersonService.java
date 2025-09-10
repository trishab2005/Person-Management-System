package com.example.service;

import com.example.entity.AadharCard;
import com.example.entity.Person;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
    }

    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new ResourceNotFoundException("Person not found with id " + id);
        }
        personRepository.deleteById(id);
    }
    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));

        // Update basic person details
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setAge(updatedPerson.getAge());

        // Handle AadharCard update
        if (updatedPerson.getAadharCard() != null) {
            AadharCard existingAadharCard = existingPerson.getAadharCard();

            // If the person already has an AadharCard
            if (existingAadharCard != null) {
                // Update the fields of the existing AadharCard
                existingAadharCard.setAadharNumber(updatedPerson.getAadharCard().getAadharNumber());
                existingAadharCard.setAddress(updatedPerson.getAadharCard().getAddress());

            } else {
                // If the person does not have an AadharCard, create and link a new one
                existingPerson.setAadharCard(updatedPerson.getAadharCard());
            }
        }

        return personRepository.save(existingPerson);
    }
}