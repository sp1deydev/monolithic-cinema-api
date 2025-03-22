package com.monolithic.cinema.service;

import com.monolithic.cinema.entity.Genre;
import com.monolithic.cinema.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {
    GenreRepository genreRepository;

    public List<Genre> getGenres() {
        return genreRepository.findAll();
//                .stream().map(genreMapper::toRoleResponse).toList();
    }

}
