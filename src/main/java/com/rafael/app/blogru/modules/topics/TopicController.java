package com.rafael.app.blogru.modules.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PreAuthorize("hasAuthority('user') or hasAuthority('creator') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping
    public ResponseEntity<Object> getAllTopics(){
        List<Topic> listTopicsDB = topicService.readAllTopics();
        List<TopicDTO> listTopicsDTO = listTopicsDB.stream()
                .map(this::convertTopicToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listTopicsDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PostMapping
    public ResponseEntity<Object> registerTopic(@RequestBody TopicDTO topicDTO){
        Topic topicDB = topicService.readTopicByName(topicDTO.getName());
        if (topicDB != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic already exists");
        }
        Topic topicCreate = topicService.createTopic(topicDTO);
        return ResponseEntity.ok().body(this.convertTopicToDTO(topicCreate));
    }

    @PreAuthorize("hasAuthority('user') or hasAuthority('creator') or hasAuthority('admin') or hasAuthority('superadmin')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTopicById(@PathVariable(value = "id") String id){
        Topic topicDB = topicService.readTopic(id);
        if (topicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic not found");
        }
        return ResponseEntity.ok().body(this.convertTopicToDTO(topicDB));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTopic(@PathVariable(value = "id") String id, @RequestBody TopicDTO topicDTO){
        Topic topicDB = topicService.readTopic(id);
        if (topicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic not found");
        }
        topicDTO.setId(id);
        Topic topicUpdate = topicService.updateTopic(topicDTO);
        return ResponseEntity.ok().body(this.convertTopicToDTO(topicUpdate));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTopic(@PathVariable(value = "id") String id){
        Topic topicDB = topicService.readTopic(id);
        if (topicDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic not found");
        }
        Topic topicDelete = topicService.deleteTopic(id);
        return ResponseEntity.ok().body(this.convertTopicToDTO(topicDelete));
    }

    private TopicDTO convertTopicToDTO(Topic topic){
        return TopicDTO.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .registerDate(topic.getRegisterDate().toString())
                .build();
    }

}
