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
        topicDto.setDescription(topic.getDescription());

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

    public Topic dtoToTopic(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }

        Topic topic = new Topic();
        topic.setTopicId(topicDto.getTopicId());
        topic.setName(topicDto.getName());
        topic.setDescription(topicDto.getDescription());

        return topic;
    }

    public List<Topic> dtoListToTopic(List<TopicDto> topicDtos) {
        List<Topic> topics = new ArrayList<>();

        for (TopicDto topicDto : topicDtos) {
            Topic topic = dtoToTopic(topicDto);

            if (topic != null) {
                topics.add(topic);
            }
        }
        return topics;
    }
}
