package com.movieflix.controller;

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

import java.util.List;


@Tag(name = "Streaming Controller", description = "Recurso reposnsavel para gerenciar serviços de streaming")
public interface StreamingInterface {
    @Operation(summary = "getAllStreaming", description = "Operações para listar todos os serviços de streaming",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de serviços de streaming retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingDTO.class))))
    ResponseEntity<List<StreamingDTO>> getAllStreaming();


    @Operation(summary = "saveStreaming", description = "Operações para criar um novo serviço de streaming",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Serviço de streaming salvo com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingDTO.class)))
    ResponseEntity<StreamingDTO> saveStreaming(@Valid @RequestBody StreamingDTO streaming);


    @Operation(summary = "getStreamingById", description = "Operações para listar o serviço de streaming por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Serviço de streaming retornado com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingDTO.class)))
    @ApiResponse(responseCode = "404", description = "Serviço de streaming não encontrado",
            content = @Content())
    ResponseEntity<?> getStreamingById(@PathVariable Long id);


    @Operation(summary = "deleteStreamingById", description = "Operações para deletar o serviço de streaming por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Serviço de streaming deletado com sucesso",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Serviço de streaming não encontrado",
            content = @Content())
    ResponseEntity<?> deleteStreamingById(@PathVariable Long id);



    @Operation(summary = "updateStreamingById", description = "Operações para atualizar o serviço de streaming por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Serviço de streaming atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingDTO.class)))
    @ApiResponse(responseCode = "404", description = "Serviço de streaming não encontrado",
            content = @Content())
    ResponseEntity<?> updateStreamingById(@PathVariable Long id, @Valid @RequestBody StreamingDTO streaming);

}
