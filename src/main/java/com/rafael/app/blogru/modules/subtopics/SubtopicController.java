package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.topics.Topic;
import com.rafael.app.blogru.modules.topics.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subtopics")
public class SubtopicController {

    @Autowired
    SubtopicService subtopicService;

    @Autowired
    TopicService topicService;

//    @PreAuthorize("hasAuthority('user') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping(value = "/read")
    public ResponseEntity<Object> getAllSubtopics(){
        List<Subtopic> subtopicListDB = subtopicService.readAllSubtopics();
        List<SubtopicDto> subtopicDtoList = subtopicListDB.stream()
                .map(this::convertSubtopictoDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(subtopicDtoList);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PostMapping
    public ResponseEntity<Object> registerSubtopic(@RequestBody SubtopicDto subtopicDTO){
        Subtopic subtopic =  subtopicService.readSubtopicByName(subtopicDTO.getName());
        if (subtopic != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic already exists");
        }
        Subtopic subtopicCreate = subtopicService.createSubtopic(subtopicDTO);
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicCreate));
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubtopicById(@PathVariable(value = "id") String id){
        Subtopic subtopicDB = subtopicService.readSubtopic(id);
        if (subtopicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic not found");
        }
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicDB));
    }

//    @PreAuthorize("hasAuthority('user') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping("/read/topic/{id}")
    public ResponseEntity<Object> readSubtopicsByTopicId(@PathVariable(value = "id") String id){

        Topic topic = topicService.readTopic(id);

        List<Subtopic> listSubtopics = subtopicService.readSubtopicsByTopic(topic);
        List<SubtopicDto> listSubtopicsDto = listSubtopics.stream()
                .map(this::convertSubtopictoDTO)
                .collect(Collectors.toList());

        if (topic == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic not found");
        }
        return ResponseEntity.ok().body(listSubtopicsDto);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubtopic(@PathVariable(value = "id") String id, @RequestBody SubtopicDto subtopicDTO){
        Subtopic subtopicDB = subtopicService.readSubtopic(id);
        if (subtopicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic not found");
        }
        subtopicDTO.setId(id);
        Subtopic subtopicUpdate = subtopicService.updateSubtopic(subtopicDTO);
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicUpdate));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubtopic(@PathVariable(value = "id") String id){
        Subtopic subtopicDB = subtopicService.readSubtopic(id);
        if (subtopicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic not found");
        }
        Subtopic subtopicDelete = subtopicService.deleteSubtopic(id);
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicDelete));
    }


    private SubtopicDto convertSubtopictoDTO(Subtopic subtopic){
        return SubtopicDto.builder()
                .id(subtopic.getId())
                .name(subtopic.getName())
                .description(subtopic.getImage())
                .registerDate(subtopic.getRegisterDate())
                .build();
    }

}
