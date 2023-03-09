package com.rafael.app.blogru.util;

import com.rafael.app.blogru.modules.subtopics.document.Subtopic;
import com.rafael.app.blogru.modules.subtopics.repository.SubtopicRepository;
import com.rafael.app.blogru.modules.subtopics.service.SubtopicService;
import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.modules.topics.repository.TopicRepository;
import com.rafael.app.blogru.modules.topics.service.TopicService;
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
        String[] mainRoles = new String[] {"user","creator","admin","superadmin"};
        for (String p:mainRoles) {
            if (roleService.readByName(p) == null){
                Role role = new Role(p);
                insertRoles.add(role);
            }
        }
        roleRepository.saveAll(insertRoles);

        List<Topic> insertTopics = new ArrayList<>();
        Map<String, String> mainTopicsMap = new HashMap<>();
        mainTopicsMap.put("Science","Science Related Topics");
        mainTopicsMap.put("Math","Math Related Topics");
        mainTopicsMap.put("Chemistry","Chemistry Related Topics");
        mainTopicsMap.put("Physics","Physics Related Topics");
        mainTopicsMap.put("Geometry","Geometry Related Topics");

        mainTopicsMap.entrySet().forEach( v -> {
                    if (topicService.readTopicByName(v.getKey()) == null){
                        Topic topic = new Topic(v.getKey(), v.getValue());
                        insertTopics.add(topic);
                    }
                });
        topicRepository.saveAll(insertTopics);


        List<Subtopic> insertSubtopics = new ArrayList<>();
        Map<String, String> mainSubtopicsMap = new HashMap<>();
        mainSubtopicsMap.put("Interesting","Interesting things");
        mainSubtopicsMap.put("Curious","Curious things");
        mainSubtopicsMap.put("Discovery","New things related to a field");
        mainSubtopicsMap.put("Big question","A big question related to a field");
        mainSubtopicsMap.put("Rememorate","Remember a specific date");

        mainSubtopicsMap.entrySet().forEach( v -> {
            if (subtopicService.readSubtopicByName(v.getKey()) == null){
                Subtopic subtopic = new Subtopic(v.getKey(), v.getValue());
                insertSubtopics.add(subtopic);
            }
        });
        subtopicRepository.saveAll(insertSubtopics);

    }
}
