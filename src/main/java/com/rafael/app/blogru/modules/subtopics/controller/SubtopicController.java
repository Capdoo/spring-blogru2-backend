package com.rafael.app.blogru.modules.subtopics.controller;

import com.rafael.app.blogru.modules.subtopics.document.Subtopic;
import com.rafael.app.blogru.modules.subtopics.dto.SubtopicDTO;
import com.rafael.app.blogru.modules.subtopics.service.SubtopicService;
import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.modules.topics.dto.TopicDTO;
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

    @PreAuthorize("hasAuthority('user') or hasAuthority('creator') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping
    public ResponseEntity<Object> getAllSubtopics(){
        List<Subtopic> subtopicListDB = subtopicService.readAllSubtopics();
        List<SubtopicDTO> subtopicDTOList = subtopicListDB.stream()
                .map(this::convertSubtopictoDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(subtopicDTOList);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PostMapping
    public ResponseEntity<Object> registerSubtopic(@RequestBody SubtopicDTO subtopicDTO){
        Subtopic subtopic =  subtopicService.readSubtopicByName(subtopicDTO.getName());
        if (subtopic != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic already exists");
        }
        Subtopic subtopicCreate = subtopicService.createSubtopic(subtopicDTO);
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicCreate));
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('creator') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubtopicById(@PathVariable(value = "id") String id){
        Subtopic subtopicDB = subtopicService.readSubtopic(id);
        if (subtopicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subtopic not found");
        }
        return ResponseEntity.ok().body(this.convertSubtopictoDTO(subtopicDB));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubtopic(@PathVariable(value = "id") String id, @RequestBody SubtopicDTO subtopicDTO){
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


    private SubtopicDTO convertSubtopictoDTO(Subtopic subtopic){
        return SubtopicDTO.builder()
                .id(subtopic.getId())
                .name(subtopic.getName())
                .description(subtopic.getDescription())
                .registerDate(subtopic.getRegisterDate())
                .build();
    }

}
