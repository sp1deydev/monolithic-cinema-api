package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.BookingRequest;
import com.monolithic.cinema.dto.Response.BookingResponse;
import com.monolithic.cinema.entity.*;
import com.monolithic.cinema.enums.BookingStatus;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.BookingMapper;
import com.monolithic.cinema.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    BookingRepository bookingRepository;

    BookingMapper bookingMapper;

    UserRepository userRepository;

    ShowtimeRepository showtimeRepository;

    TicketRepository ticketRepository;

    SeatRepository seatRepository;

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "User"));
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Showtime"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setStatus("PENDING");
        //bookingRepository.save(booking);
        final Booking savedBooking = bookingRepository.save(booking);
        List<Ticket> tickets = request.getSeatId().stream().map(seatId -> {
            Seat seat = seatRepository.findById(seatId).orElseThrow();
            Ticket ticket = new Ticket();
            ticket.setBooking(savedBooking);
            ticket.setSeat(seat);
            return ticketRepository.save(ticket);
        }).collect(Collectors.toList());
        savedBooking.setTickets(tickets);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponse getBookingById(String id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toBookingResponse)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Transactional
    public BookingResponse updateBookingStatus(String id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    @Transactional
    public List<BookingResponse> getBookingsByUserAndStatus(String userId, String status) {
        List<Booking> bookings = bookingRepository.findBookingByUserAndStatus(userId, status);
        return bookings.stream().map(bookingMapper::toBookingResponse).collect(Collectors.toList());
    }

    @Transactional
    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Kiểm tra điều kiện chỉ xóa khi chưa thanh toán (PAID)
        if ("PAID".equals(booking.getStatus())) {
            throw new RuntimeException("Cannot delete a paid booking.");
        }

        bookingRepository.delete(booking);
    }

}
