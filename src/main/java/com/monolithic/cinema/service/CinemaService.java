package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.CinemaRequest;
import com.monolithic.cinema.dto.Response.CinemaResponse;
import com.monolithic.cinema.entity.Cinema;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.CinemaMapper;
import com.monolithic.cinema.repository.CinemaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaService {
    CinemaRepository cinemaRepository;
    CinemaMapper cinemaMapper;

    public List<CinemaResponse> getCinemas() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::toCinemaResponse).toList();
    }

    public int countCinemas() {
        return (int) cinemaRepository.count();
    }

    public CinemaResponse getCinema(String id) {
        return cinemaMapper.toCinemaResponse(cinemaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Cinema")));
    }

    public CinemaResponse createCinema(CinemaRequest request) {
        if(cinemaRepository.existsByName(request.getName())) {
            throw new CustomException(ErrorCode.RESOURCE_ALREADY_EXISTS, "Cinema");
        }
        return cinemaMapper.toCinemaResponse(cinemaRepository.save(cinemaMapper.toCinema(request)));
    }

    public CinemaResponse updateCinema(String id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Cinema"));
        cinemaMapper.updateCinema(cinema, request);
        return cinemaMapper.toCinemaResponse(cinemaRepository.save(cinema));
    }

    @Transactional
    public void deleteCinema(String id) {
        cinemaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "Cinema"));
        cinemaRepository.deleteById(id);
    }
}
