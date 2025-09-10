package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    @Query("SELECT p FROM Person p WHERE p.name = :name")
    Optional<Person> findByName(String name);
    
    @Query("SELECT p FROM Person p WHERE p.aadharCard.aadharNumber = :aadharNumber")
    Optional<Person> findByAadharNumber(String aadharNumber); 

}
