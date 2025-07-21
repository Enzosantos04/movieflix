package com.movieflix.mapper;


import com.movieflix.dto.CategoryDTO;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.StreamingDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {


    private final CategoryMapper categoryMapper;
private final StreamingMapper streamingMapper;

    public MovieMapper(CategoryMapper categoryMapper, StreamingMapper streamingMapper) {
        this.categoryMapper = categoryMapper;
        this.streamingMapper = streamingMapper;
    }

    public Movie map(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        //converter de LIST CategoryDTO para category
        if(movie.getCategories() != null){
            List<Category> categories = movieDTO.getCategories()
                    .stream()
                    .map(categoryMapper::map)
                    .collect(Collectors.toList());
            movie.setCategories(categories);
        }

        //converter de LIST StreamingDTO para streaming
        if(movie.getStreamings() != null){
            List<Streaming> streamings = movieDTO.getStreamings()
                    .stream()
                    .map(streamingMapper::map)
                    .collect(Collectors.toList());
            movie.setStreamings(streamings);
        }


        return movie;
    }


    public MovieDTO map(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
       movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setRating(movie.getRating());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setCreatedAt(movie.getCreatedAt());
        movieDTO.setUpdatedAt(movie.getUpdatedAt());

        //converter de LIST Categoru para categoryDTO
        if(movieDTO.getCategories() != null){
            List<CategoryDTO> categoryDTOS = movie.getCategories()
                    .stream()
                    .map(categoryMapper::map)
                    .collect(Collectors.toList());
            movieDTO.setCategories(categoryDTOS);
        }

        //converter de List Streaming para StreamingDTO
        if(movieDTO.getStreamings() != null){
            List<StreamingDTO> streamingDTOS = movie.getStreamings()
                    .stream()
                    .map(streamingMapper::map)
                    .collect(Collectors.toList());
            movieDTO.setStreamings(streamingDTOS);
        }
        return movieDTO;
    }



}
