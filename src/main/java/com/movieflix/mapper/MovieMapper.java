package com.movieflix.mapper;


import com.movieflix.dto.CategoryDTO;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.StreamingDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {


    private final CategoryMapper categoryMapper;
private final StreamingMapper streamingMapper;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieMapper(CategoryMapper categoryMapper, StreamingMapper streamingMapper, CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.categoryMapper = categoryMapper;
        this.streamingMapper = streamingMapper;
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }

    public Movie map(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setRating(movieDTO.getRating());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        //converter de LIST CategoryDTO para category
        if(movieDTO.getCategories() != null){
            List<Category> categories = movieDTO.getCategories()
                    .stream()
                    .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                            .orElseThrow(()-> new RuntimeException("Category not found")))
                    .collect(Collectors.toList());
            movie.setCategories(categories);
        }

        //converter de LIST StreamingDTO para streaming
        if(movieDTO.getStreamings() != null){
            List<Streaming> streamings = movieDTO.getStreamings()
                    .stream()
                    .map(streamingDTO -> streamingRepository.findById(streamingDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Streaming Not Found")))
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

        // converter de List<Category> para List<CategoryDTO>
        if (movie.getCategories() != null) {
            List<CategoryDTO> categoryDTOS = movie.getCategories()
                    .stream()
                    .map(categoryMapper::map)
                    .collect(Collectors.toList());
            movieDTO.setCategories(categoryDTOS);
        }

        // converter de List<Streaming> para List<StreamingDTO>
        if (movie.getStreamings() != null) {
            List<StreamingDTO> streamingDTOS = movie.getStreamings()
                    .stream()
                    .map(streamingMapper::map)
                    .collect(Collectors.toList());
            movieDTO.setStreamings(streamingDTOS);
        }

        return movieDTO;
    }

    }




