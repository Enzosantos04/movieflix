package com.movieflix.mapper;



import com.movieflix.dto.StreamingDTO;
import com.movieflix.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper{

    public Streaming map(StreamingDTO streamingDTO){
        Streaming streaming =  new Streaming();
        streaming.setId(streamingDTO.getId());
        streaming.setName(streamingDTO.getName());
        return streaming;
    }


    public StreamingDTO map(Streaming streaming){
        StreamingDTO streamingDTO = new StreamingDTO();
        streamingDTO.setId(streaming.getId());
        streamingDTO.setName(streaming.getName());
        return streamingDTO;
    }
}
