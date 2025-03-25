package com.monolithic.cinema.repository;

import com.monolithic.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {
    boolean existsByName(String name);
}
