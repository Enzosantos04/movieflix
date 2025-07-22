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

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        if(movieService.getMovieById(id) != null){
            MovieDTO movieDTO = movieService.getMovieById(id);
            return ResponseEntity.ok(movieDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found.");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id){
        if(movieService.getMovieById(id) != null){
            movieService.deleteMovieById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found");
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStreamingById(@PathVariable Long id, @RequestBody MovieDTO movie){
        if (movieService.getMovieById(id) != null ){
            MovieDTO movieDTO = movieService.updateMovieById(id, movie);
            return ResponseEntity.ok(movieDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> findByCategory(@RequestParam Long category){
        if(movieService.findMovieByCategory(category) != null){
            List<MovieDTO> movieDTO = movieService.findMovieByCategory(category);
            return ResponseEntity.ok(movieDTO);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
