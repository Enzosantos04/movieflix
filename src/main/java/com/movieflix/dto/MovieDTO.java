package com.movieflix.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

    private Long id;
    //annotation para validar o campo title, se estiver vazio, retorna a mensagem "Title cannot be empty"
    @NotEmpty(message = "Title cannot be empty")
   @Schema(type = "string", description = "Titulo do filme", example = "nome do filme")
    private String title;
    @Schema(type = "string", description = "descricao do filme")
    private String description;
    //annotation para mudar o padrao da data.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(type = "date", description = "data de lancamento do filme", example = "01/01/2023")
    private LocalDate releaseDate;
    @Schema(type = "double", description = "avaliacao do filme", example = "8.5")
    private double rating;
    @Schema(type = "string", description = "lista de categorias do filme")
    private List<CategoryDTO> categories;
    @Schema(type = "string", description = "lista de servico de streaming do filme")
    private List<StreamingDTO> streamings;

}
