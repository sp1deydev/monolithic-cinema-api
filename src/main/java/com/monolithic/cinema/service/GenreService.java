package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.GenreRequest;
import com.monolithic.cinema.dto.Response.GenreDetailResponse;
import com.monolithic.cinema.dto.Response.GenreResponse;
import com.monolithic.cinema.entity.Genre;
import com.monolithic.cinema.mapper.GenreMapper;
import com.monolithic.cinema.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {
    GenreRepository genreRepository;
    GenreMapper genreMapper;

    public List<GenreResponse> getGenres() {
        return genreRepository.findAll()
                .stream().map(genreMapper::toGenreResponse).toList();
    }

    public GenreDetailResponse getGenre(String id) {
        return genreMapper.toGenreDetailResponse(genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre Not Found!")));

    }

    public int countGenres() {
        return (int) genreRepository.count();
    }

    public GenreResponse createGenre(GenreRequest request) {
        return genreMapper.toGenreResponse(genreRepository.save(genreMapper.toGenre(request)));
    }

    public GenreResponse updateGenre(String id, GenreRequest request) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre Not Found!"));
        genreMapper.updateGenre(genre, request);
        return genreMapper.toGenreResponse(genreRepository.save(genre));
    }

    public void deleteGenre(String id) {
        genreRepository.deleteById(id);
    }

}
