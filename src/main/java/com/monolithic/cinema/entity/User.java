package com.monolithic.cinema.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    String email;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookings;

}
