package Controllers;

import com.openclassrooms.mddapi.controllers.TopicController;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import com.openclassrooms.mddapi.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TopicControllerTest {

    @InjectMocks
    private TopicController topicController;

    @Mock
    private TopicService topicService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private TopicMapper topicMapper;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return list of topics when topics are fetched")
    public void getTopics_Success() {
        TopicDto topicDto = new TopicDto();
        topicDto.setName("Test topic");

        when(topicService.getTopics()).thenReturn(Collections.singletonList(new Topic()));
        when(topicMapper.topicListToDto(any(List.class))).thenReturn(Collections.singletonList(topicDto));

        List<TopicDto> response = topicController.getTopics();

        assertEquals(1, response.size());
        assertEquals(topicDto.getName(), response.get(0).getName());
    }

    @Test
    @DisplayName("Should return list of subscribed topics when subscriptions are fetched")
    public void getSubscriptions_Success() {
        TopicDto topicDto = new TopicDto();
        topicDto.setName("Test topic");

        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(subscriptionService.getSubscriptions(any(Long.class))).thenReturn(Collections.singletonList(1L));
        when(topicService.getTopicsByIds(any(List.class))).thenReturn(Collections.singletonList(new Topic()));
        when(topicMapper.topicListToDto(any(List.class))).thenReturn(Collections.singletonList(topicDto));

        List<TopicDto> response = topicController.getSubscriptions();

        assertEquals(1, response.size());
        assertEquals(topicDto.getName(), response.get(0).getName());
    }

    @Test
    @DisplayName("Should subscribe to a topic successfully")
    public void subscribe_Success() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);

        topicController.subscribe(1L);
    }

    @Test
    @DisplayName("Should unsubscribe from a topic successfully")
    public void unsubscribe_Success() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);

        topicController.unsubscribe(1L);
    }
}
