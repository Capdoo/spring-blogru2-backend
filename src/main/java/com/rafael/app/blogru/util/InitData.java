package com.rafael.app.blogru.util;

import com.rafael.app.blogru.modules.subtopics.Subtopic;
import com.rafael.app.blogru.modules.subtopics.SubtopicRepository;
import com.rafael.app.blogru.modules.subtopics.SubtopicService;
import com.rafael.app.blogru.modules.topics.Topic;
import com.rafael.app.blogru.modules.topics.TopicRepository;
import com.rafael.app.blogru.modules.topics.TopicService;
import com.rafael.app.blogru.security.document.Role;
import com.rafael.app.blogru.security.repository.RoleRepository;
import com.rafael.app.blogru.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TopicService topicService;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    SubtopicService subtopicService;
    @Autowired
    SubtopicRepository subtopicRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Role> insertRoles = new ArrayList<>();
        String[] mainRoles = new String[] {"user","admin","superadmin"};
        for (String p:mainRoles) {
            if (roleService.readByName(p) == null){
                Role role = new Role(p);
                insertRoles.add(role);
            }
        }
        roleRepository.saveAll(insertRoles);

        List<Topic> insertTopics = new ArrayList<>();
        Map<String, String> mainTopicsMap = new HashMap<>();
        mainTopicsMap.put("Matemáticas","mathematics");
        mainTopicsMap.put("Ciencias","science");
        mainTopicsMap.put("Humanidades","earth");
        mainTopicsMap.put("Letras","social");

        mainTopicsMap.entrySet().forEach( v -> {
                    if (topicService.readTopicByName(v.getKey()) == null){
                        Topic topic = new Topic(v.getKey(), v.getValue());
                        insertTopics.add(topic);
                    }
                });
        topicRepository.saveAll(insertTopics);

        List<Subtopic> insertSubtopics = new ArrayList<>();
        Map<String, String> mainSubtopicsMap = new HashMap<>();
        mainSubtopicsMap.put("Aritmética","arithmetics:Matemáticas");
        mainSubtopicsMap.put("Geometría","geometry:Matemáticas");
        mainSubtopicsMap.put("Álgebra","algebra:Matemáticas");
        //
        mainSubtopicsMap.put("Química","chemistry:Ciencias");
        mainSubtopicsMap.put("Física","physics:Ciencias");
        mainSubtopicsMap.put("Biología","biology:Ciencias");
        //
        mainSubtopicsMap.put("Historia","history:Humanidades");
        mainSubtopicsMap.put("Astronomía","telescope:Humanidades");
        mainSubtopicsMap.put("Geografía","geography:Humanidades");
        //
        mainSubtopicsMap.put("Lenguaje","languages:Letras");
        mainSubtopicsMap.put("Literatura","literature:Letras");
        mainSubtopicsMap.put("Ortografía","spelling-bee:Letras");

        mainSubtopicsMap.entrySet().forEach( v -> {
            if (subtopicService.readSubtopicByName(v.getKey()) == null){
                String icon = v.getValue().split(":")[0];
                String topicName = v.getValue().split(":")[1];
                Topic topic = topicService.readTopicByName(topicName);
                Subtopic subtopic = new Subtopic(v.getKey(), icon);
                subtopic.setTopic(topic);
                insertSubtopics.add(subtopic);
            }
        });
        subtopicRepository.saveAll(insertSubtopics);
    }
}