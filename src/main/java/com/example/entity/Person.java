package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value= 18,message = "Age must be >=18")
    private int age;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aadhar_id", referencedColumnName = "id")
    private AadharCard aadharCard;
}
