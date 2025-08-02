package com.movieflix.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamingDTO {
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
