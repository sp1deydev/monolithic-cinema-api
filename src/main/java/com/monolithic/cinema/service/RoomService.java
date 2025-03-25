package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.RoomRequest;
import com.monolithic.cinema.dto.Response.RoomResponse;
import com.monolithic.cinema.entity.Room;
import com.monolithic.cinema.entity.Seat;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.RoomMapper;
import com.monolithic.cinema.repository.CinemaRepository;
import com.monolithic.cinema.repository.RoomRepository;
import com.monolithic.cinema.repository.SeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class RoomService {
    RoomRepository roomRepository;
    SeatRepository seatRepository;
    CinemaRepository cinemaRepository;
    RoomMapper roomMapper;

    public List<RoomResponse> getRooms() {
        return roomRepository.findAll().stream().map(roomMapper::toListRoomResponse).toList();
    }

    public RoomResponse getRoom(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Room"));
        return roomMapper.toRoomResponse(room);
    }


    public RoomResponse createRoom(RoomRequest request) {
        Room newRoom = roomMapper.toRoom(request);
        newRoom.setCinema(cinemaRepository.findById(request.getCinemaId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Cinema")));
        Room room = roomRepository.save(newRoom);

        // Generate seats (5 rows, 8 seats per row)
        List<Seat> seats = new ArrayList<>();
        for (char line = 'A'; line < 'F'; line++) {  // Rows A-E
            for (int number = 1; number <= 8; number++) {  // 8 seats per row
                Seat seat = Seat.builder()
                        .line(String.valueOf(line))
                        .number(number)
                        .room(room)
                        .build();
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats); // Save all seats

        room.setSeats(seats); // Attach seats to room
        return roomMapper.toRoomResponse(room);
    }

    public RoomResponse updateRoom(String id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Room"));
        roomMapper.updateRoom(room, request);
        return roomMapper.toRoomResponse(roomRepository.save(room));
    }
}
