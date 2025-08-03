package com.movieflix.controller;

import com.movieflix.dto.CategoryDTO;
import com.movieflix.dto.MovieDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Tag(name = "Category Controller", description = "Recurso responsável para gerenciar categorias de filmes")
public interface CategoryInterface {


    @Operation(summary = "findAllCategories", description = "Operações para listar todos os filmes",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class))))
    ResponseEntity<List<CategoryDTO>> getAllCategories();

    @Operation(summary = "saveCategory", description = "Operações para criar uma nova categoria",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
    ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO category);


    @Operation(summary = "getCategoryById", description = "Operações para listar a categoria por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria retornada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    ResponseEntity<?> getCategoryById(@PathVariable Long id);

    @Operation(summary = "deleteCategoryById", description = "Operações para deletar a categoria por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso", content = @Content())
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    ResponseEntity<?> deleteCategoryById(@PathVariable Long id);

    @Operation(summary = "updateCategoryById", description = "Operações para atualizar a categoria por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    ResponseEntity<?> updateCategoryById(@PathVariable Long id, @Valid @RequestBody CategoryDTO category);





}
