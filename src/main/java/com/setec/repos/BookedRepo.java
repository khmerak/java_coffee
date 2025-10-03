package com.setec.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.setec.entities.Booked;   // adjust package if needed

public interface BookedRepo extends JpaRepository<Booked, Integer> {
    // JpaRepository already has save(), findById(), delete(), etc.
}
