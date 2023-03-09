package com.rafael.app.blogru.util;

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
//        String[] mainTopics = new String[] {"Science","Math","Chemistry","Physics","Geometry"};
//        for (String p:mainTopics){
//            if (topicService.readTopicByName(p) == null){
//                Topic topic = new Topic(p, mainTopicsMap.get(p));
//                insertTopics.add(topic);
//            }
//        }
        topicRepository.saveAll(insertTopics);
    }
}
