package com.monolithic.cinema.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "booking_id")
    String bookingId;

    @Column(name = "amount")
    Double amount;

    @Column(name = "status")
    String status;

    @Column(name = "payment_method")
    String paymentMethod;

}
