package com.movieflix.service;

import com.movieflix.dto.MovieDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.repository.MovieRepository;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }

    public List<MovieDTO> findAll(){
        List<Movie> movies = movieRepository.findAll();
       return movies.stream()
                .map(movieMapper::map)
                .collect(Collectors.toList());

    }

    public MovieDTO saveMovie(MovieDTO movieDTO){
        Movie newMovie = movieMapper.map(movieDTO);
        newMovie = movieRepository.save(newMovie);
        return movieMapper.map(newMovie);
    }



}
