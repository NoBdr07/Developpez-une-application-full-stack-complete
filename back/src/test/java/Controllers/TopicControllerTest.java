package Controllers;

import com.openclassrooms.mddapi.controllers.TopicController;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.dtos.TopicDto;
import com.openclassrooms.mddapi.models.entities.Topic;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.security.services.CustomUserDetailsService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TopicControllerTest {

    @Mock
    private TopicService topicService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private TopicMapper topicMapper;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TopicController topicController;

    private User testUser;
    private Topic testTopic;
    private TopicDto testTopicDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        testUser.setUsername("testuser");

        testTopic = new Topic();
        testTopic.setTopicId(1L);
        testTopic.setName("Test Topic");
        testTopic.setDescription("Test Description");

        testTopicDto = new TopicDto();
        testTopicDto.setName("Test Topic");
        testTopicDto.setDescription("Test Description");
    }

    @Test
    void getTopics_ShouldReturnListOfTopicDtos() {
        when(topicService.getTopics()).thenReturn(Arrays.asList(testTopic));
        when(topicMapper.topicListToDto(any(List.class))).thenReturn(Arrays.asList(testTopicDto));

        List<TopicDto> result = topicController.getTopics();

        assertEquals(1, result.size());
        assertEquals("Test Topic", result.get(0).getName());
        verify(topicService, times(1)).getTopics();
    }

    @Test
    void getSubscriptions_ShouldReturnListOfTopicDtos() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(subscriptionService.getSubscriptions(any(User.class))).thenReturn(Arrays.asList(testTopic));
        when(topicMapper.topicListToDto(any(List.class))).thenReturn(Arrays.asList(testTopicDto));

        List<TopicDto> result = topicController.getSubscriptions();

        assertEquals(1, result.size());
        assertEquals("Test Topic", result.get(0).getName());
        verify(subscriptionService, times(1)).getSubscriptions(any(User.class));
    }

    @Test
    void subscribe_ShouldInvokeSubscribeMethod() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(topicService.getTopicById(1L)).thenReturn(Optional.of(testTopic));

        topicController.subscribe(1L);

        verify(topicService, times(1)).subscribe(any(Topic.class), any(User.class));
    }

    @Test
    void unsubscribe_ShouldInvokeUnsubscribeMethod() {
        when(userDetailsService.getCurrentUserId()).thenReturn(1L);
        when(userService.getUserFromId(1L)).thenReturn(Optional.of(testUser));
        when(topicService.getTopicById(1L)).thenReturn(Optional.of(testTopic));

        topicController.unsubscribe(1L);

        verify(topicService, times(1)).unsubscribe(any(Topic.class), any(User.class));
    }
}

