package com.movieflix.controller;


import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.StreamingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Movie Controller", description = "Recurso responsavel para gerenciar filmes")
public interface MovieInterface {


    @Operation(summary = "saveMovie", description = "Operações para criar um novo filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
            content = @Content(schema = @Schema(implementation = MovieDTO.class)))
    ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movie);



    @Operation(summary = "findAllMovies", description = "Operações para listar todos os filmes",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingDTO.class))))
    ResponseEntity<List<MovieDTO>> findAll();



    @Operation(summary = "getMovieById", description = "Operações para listar o filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme retornado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieDTO.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<?> getMovieById(@PathVariable Long id);


    @Operation(summary = "deleteMovieById", description = "Operações para deletar o filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<?> deleteMovieById(@PathVariable Long id);


    @Operation(summary = "findByCategory", description = "Operações para buscar o filme por Categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "filme por categoria retornado com sucesso", content = @Content(array = @ArraySchema(schema =
    @Schema(implementation = MovieDTO.class))))
    ResponseEntity<List<MovieDTO>> findByCategory(@RequestParam Long category);

@Operation(summary = "updateMovieById", description = "Operações para atualizar o filme por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
@ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))))
@ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<?> updateMovieById(@PathVariable Long id, @Valid @RequestBody MovieDTO movie);


}
