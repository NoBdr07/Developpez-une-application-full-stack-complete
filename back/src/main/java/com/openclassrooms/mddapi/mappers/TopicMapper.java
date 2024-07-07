package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicMapper {

    /** Maps a Topic entity to a TopicDto
     * @param topic The Topic entity to map
     * @return The TopicDto
     */
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

    /** Maps a list of Topic entities to a list of TopicDtos
     * @param topics The list of Topic entities to map
     * @return The list of TopicDtos
     */
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
