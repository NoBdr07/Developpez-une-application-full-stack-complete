package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicMapper {
    public TopicDto topicToDto(Topic topic) {
        if (topic == null) {
            return null;
        }

        TopicDto topicDto = new TopicDto();
        topicDto.setTopicId(topic.getTopicId());
        topicDto.setName(topic.getName());

        return topicDto;
    }

    public List<TopicDto> topicListToDto(List<Topic> topics) {
        List<TopicDto> topicDtos = new ArrayList<>();

        for (Topic topic : topics) {
            TopicDto topicDto = topicToDto(topic);

            if (topicDto != null) {
                topicDtos.add(topicDto);
            }
        }
        return topicDtos;
    }
}
