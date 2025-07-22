package com.movieflix.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String title;
    private String description;
    //annotation para mudar o padrao da data.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private double rating;
    private List<CategoryDTO> categories;
    private List<StreamingDTO> streamings;

}
