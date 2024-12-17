package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.sections.Section;
import com.rafael.app.blogru.modules.sections.SectionDto;
import com.rafael.app.blogru.modules.sections.SectionService;
import com.rafael.app.blogru.modules.subtopics.Subtopic;
import com.rafael.app.blogru.modules.subtopics.SubtopicService;
import com.rafael.app.blogru.modules.topics.Topic;
import com.rafael.app.blogru.modules.topics.TopicService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    TopicService topicService;
    @Autowired
    SubtopicService subtopicService;
    @Autowired
    UserService userService;
    @Autowired
    SectionService sectionService;

    @Override
    public List<Post> readAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(PostDto postDTO) {
        Post postCreate;
        Topic topic;
        Subtopic subtopic;
        User user;
        List<Section> listSections;

        topic = topicService.readTopic(postDTO.getTopicId());
        subtopic = subtopicService.readSubtopic(postDTO.getSubtopicId());
        user = userService.findById(postDTO.getUserId());
        listSections = postDTO.getListSectionsDto().stream()
                .map(this.sectionService::createSection)
                .collect(Collectors.toList());

        postCreate = Post.builder()
                .title(postDTO.getTitle())
                .summary(postDTO.getSummary())
                .topic(topic)
                .subtopic(subtopic)
                .user(user)
                .registerDate(new Date())
                .listSections(listSections)
                .estId(new BigDecimal(1))
                .build();
        return postRepository.save(postCreate);
    }

    //First time
//    @Override
    public Post createPostVo(PostDto postDTO) {
        Topic topicSelected = topicService.readTopic(postDTO.getTopicId());
        Subtopic subtopicSelected = subtopicService.readSubtopic(postDTO.getSubtopicId());
        User userCreator = userService.findById(postDTO.getUserId());


        //Assign id to paragraphs


        Post postCreate = Post.builder()
                .title(postDTO.getTitle())
                .summary(postDTO.getSummary())
                .topic(topicSelected)
                .subtopic(subtopicSelected)
                .user(userCreator)
                .registerDate(new Date())
                //content
//                .headers(postDTO.getHeaders())
//                .paragraphs(postDTO.getParagraphs())
                .build();
        return postRepository.save(postCreate);
    }

    @Override
    public Post readPost(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post updatePost(PostDto postDto) {
        Post postDb;
        Topic topic;
        Subtopic subtopic;


        postDb = readPost(postDto.getId());
        if (postDb == null){
            return null;
        }
        //general
        topic = topicService.readTopic(postDto.getTopicId());
        if (topic == null) {
            return null;
        }
        subtopic = subtopicService.readSubtopic(postDto.getSubtopicId());
        if (subtopic == null) {
            return null;
        }
        postDb.setTitle(postDto.getTitle());
        postDb.setSummary(postDto.getSummary());
        postDb.setTopic(topic);
        postDb.setSubtopic(subtopic);

        this.deleteSections(postDb, postDto);

        List<Section> listSectionDb = new ArrayList<>();
        for (SectionDto sectionDto: postDto.getListSectionsDto()) {
            if (sectionDto.getId() == null) {
                //create
                listSectionDb.add(this.sectionService.createSection(sectionDto));
                //assign to post
            } else {
                //update
                listSectionDb.add(this.sectionService.updateSection(sectionDto));
                //trabajar sobre la actualizacion de header y lista de poarrafos pero en seccion
            }
        }

        postDb.setListSections(listSectionDb);
        return postRepository.save(postDb);
    }

    private void deleteSections(Post postDb, PostDto postDto) {
        List<String> listSectionsIdDb;
        List<String> listSectionsIdDto;
        List<String> listSectionsIdDelete;

        //eliminacion
        listSectionsIdDb = postDb.getListSections().stream()
                .map(Section::getId)
                .collect(Collectors.toList());

        listSectionsIdDto = postDto.getListSectionsDto()
                .stream()
                .map(SectionDto::getId)
                .collect(Collectors.toList());

        listSectionsIdDelete = listSectionsIdDb.stream()
                .filter(e -> !listSectionsIdDto.contains(e))
                .collect(Collectors.toList());

        if (!listSectionsIdDelete.isEmpty()) {
            this.sectionService.deleteAllSections(listSectionsIdDelete);
        }

    }

    @Override
    public Post deletePost(String id) {
        Post postDB = readPost(id);
        if (postDB == null){
            return null;
        }
        postRepository.deleteById(id);
        return postDB;
    }

    //business
    @Override
    public Post readByTitle(String title) {
        return postRepository.findByTitle(title).orElse(null);
    }

    @Override
    public List<Post> readByUser(User user) {
        return postRepository.findAllByUser(user);
    }

}