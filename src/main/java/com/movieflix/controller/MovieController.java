package com.movieflix.controller;


import com.movieflix.dto.MovieDTO;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@Tag(name = "Movie", description = "Recurso responsavel para gerenciar filmes")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @ApiResponse(responseCode = "201", description = "filme salvo com sucesso", content = @Content(schema = @Schema(implementation = MovieDTO.class)))
    @Operation(summary = "saveMovie", description = "Operações para criar um novo filme", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movie){
        MovieDTO movieDTO = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
    }


    @Operation(summary = "findAllMovies", description = "Operações para listar todos os filmes",
    security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))))
    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(){
        List<MovieDTO> movieDTOS = movieService.findAll();
        return ResponseEntity.ok(movieDTOS);
    }

    @Operation(summary = "getMovieById", description = "Operações para listar o filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso", content = @Content(schema= @Schema(implementation = MovieDTO.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        if(movieService.getMovieById(id) != null){
            MovieDTO movieDTO = movieService.getMovieById(id);
            return ResponseEntity.ok(movieDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found.");

        }
    }

    @Operation(summary = "deleteMovieById", description = "Operaçao para deletar filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "filme deletado sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id){
        if(movieService.getMovieById(id) != null){
            movieService.deleteMovieById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found");
        }
    }


    @Operation(summary = "updateMovieById", description = "Operações para atualizar o filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "filme alterado com sucesso",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable Long id, @Valid @RequestBody MovieDTO movie){
        if (movieService.getMovieById(id) != null ){
            MovieDTO movieDTO = movieService.updateMovieById(id, movie);
            return ResponseEntity.ok(movieDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie Not Found");
        }
    }

    @Operation(summary = "findByCategory", description = "Operações para listar filmes por categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes por categoria retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))))
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
