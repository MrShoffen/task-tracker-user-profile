package org.mrshoffen.tasktracker.user.profile.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.user.UserCreatedEvent;
import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventPublisher {

    private final KafkaTemplate<UUID, Object> kafkaTemplate;

    public void publishUserCreated(User user, String ipAddr) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userIpAddr(ipAddr)
                .build();

        kafkaTemplate.send(UserCreatedEvent.TOPIC, userCreatedEvent.getId(), userCreatedEvent);
        log.info("Event published to kafka topic '{}' - {}", UserCreatedEvent.TOPIC, userCreatedEvent);
    }

}
