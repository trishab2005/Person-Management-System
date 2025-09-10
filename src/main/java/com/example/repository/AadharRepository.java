package com.example.repository;

import com.example.entity.AadharCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharRepository extends JpaRepository<AadharCard, Long> {
}
