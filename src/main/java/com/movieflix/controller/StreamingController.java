package com.movieflix.controller;


import com.movieflix.dto.StreamingDTO;
import com.movieflix.service.StreamingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping
    public ResponseEntity<List<StreamingDTO>> getAllStreaming(){
        List<StreamingDTO> streamings = streamingService.findAll();
        return ResponseEntity.ok(streamings);
    }

    @PostMapping
    public ResponseEntity<StreamingDTO> saveStreaming(@RequestBody StreamingDTO streaming){
        StreamingDTO streamingDTO = streamingService.saveStreaming(streaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(streamingDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStreamingById(@PathVariable Long id){
        if(streamingService.getStreamingById(id) != null){
           StreamingDTO streamingDTO = streamingService.getStreamingById(id);
           return ResponseEntity.ok(streamingDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streaming Not Found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStreamingById(@PathVariable Long id){
        if(streamingService.getStreamingById(id) != null){
            streamingService.deleteStreamingById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streaming Not Found");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStreamingById(@PathVariable Long id, @RequestBody StreamingDTO streaming){
        if(streamingService.getStreamingById(id)!= null){
            StreamingDTO streamingDTO = streamingService.updateStreamingById(id, streaming);
            return ResponseEntity.ok(streamingDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Streaming Not Found");
        }
    }




}
