package com.monolithic.cinema.repository;

import com.monolithic.cinema.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    boolean existsById(String id);
}