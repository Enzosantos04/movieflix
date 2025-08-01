package com.movieflix.service;

import com.movieflix.dto.StreamingDTO;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public StreamingService(StreamingRepository streamingRepository, StreamingMapper streamingMapper) {
        this.streamingRepository = streamingRepository;
        this.streamingMapper = streamingMapper;
    }

    public List<StreamingDTO> findAll(){
        List<Streaming>  streaming = streamingRepository.findAll();
        return streaming.stream()
                .map(streamingMapper::map)
                .collect(Collectors.toList());

    }

    public StreamingDTO saveStreaming(StreamingDTO streamingDTO){
        Streaming newStreaming = streamingMapper.map(streamingDTO);
        newStreaming = streamingRepository.save(newStreaming);
        return streamingMapper.map(newStreaming);
    }

    public StreamingDTO getStreamingById(Long id){
        Optional<Streaming> streamingById = streamingRepository.findById(id);
        return streamingById.map(streamingMapper::map).orElse(null);

    }

    public void deleteStreamingById(Long id){
        streamingRepository.deleteById(id);
    }

    public StreamingDTO updateStreamingById(Long id, StreamingDTO streamingDTO){
        Optional<Streaming> existingStreaming = streamingRepository.findById(id);
        if(existingStreaming.isPresent()){
            Streaming updatedStreaming = streamingMapper.map(streamingDTO);
            updatedStreaming.setId(id);
            Streaming streamingSaved = streamingRepository.save(updatedStreaming);
            return streamingMapper.map(streamingSaved);
        }
        return null;
    }
}
