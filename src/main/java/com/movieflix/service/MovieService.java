package com.movieflix.service;

import com.movieflix.dto.MovieDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;

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

    public MovieDTO getMovieById(Long id){
        Optional<Movie> movies = movieRepository.findById(id);
        return movies.map(movieMapper::map).orElse(null);
    }

    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }

    public MovieDTO updateMovieById(Long id, MovieDTO movieDTO){
      Optional<Movie> existingMovie = movieRepository.findById(id);
      if(existingMovie.isPresent()){
          Movie updatedMovie = movieMapper.map(movieDTO);
          updatedMovie.setId(id);
          Movie movieSaved = movieRepository.save(updatedMovie);
          return movieMapper.map(movieSaved);
      }

      return null;
    }

    public List<MovieDTO> findMovieByCategory(Long categoryId){
        List<Movie> moviesByCategory = movieRepository.findMovieByCategories_Id(categoryId);
        return moviesByCategory.stream()
                .map(movieMapper::map)
                .collect(Collectors.toList());
    }



}
