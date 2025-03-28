package com.monolithic.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "showtime")
    Instant showtime;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    Room room;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookings;
}
