package com.movieflix.controller;


import com.movieflix.dto.MovieDTO;
import com.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movie){
        MovieDTO movieDTO = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(){
        List<MovieDTO> movieDTOS = movieService.findAll();
        return ResponseEntity.ok(movieDTOS);
    }
}
