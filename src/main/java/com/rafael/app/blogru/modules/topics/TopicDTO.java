package com.rafael.app.blogru.modules.topics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TopicDTO {
    private String id;
    private String name;
    private String description;
    private String registerDate;
}
