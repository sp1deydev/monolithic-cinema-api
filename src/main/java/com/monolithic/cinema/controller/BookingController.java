package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.BookingRequest;
import com.monolithic.cinema.dto.Response.BookingResponse;
import com.monolithic.cinema.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable String id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBookingStatus(@PathVariable String id, @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getBookingsByUserAndStatus(
            @RequestParam String userId, @RequestParam(required = false) String status) {
        return ResponseEntity.ok(bookingService.getBookingsByUserAndStatus(userId, status));
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkBookingsByUserAndStatus(
            @RequestParam String userId, @RequestParam String status) {
        List<BookingResponse> bookings = bookingService.getBookingsByUserAndStatus(userId, status);
        return bookings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successful");
    }
}
