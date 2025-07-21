package com.movieflix.dto;


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
    private LocalDate releaseDate;
    private double rating;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private List<CategoryDTO> categories;
    private List<StreamingDTO> streamings;

}
