package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AadharCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Aadhar number is required")
    @Size(min = 12, max = 12, message = "Aadhar must be 12 digits")
    @Column(unique = true, nullable = false)
    private String aadharNumber;
    @NotBlank(message = "Address is required")
    private String address;

}
