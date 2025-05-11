package org.mrshoffen.tasktracker.user.profile.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationAttemptEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationFailedEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationSuccessfulEvent;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final UserService userService;

    @KafkaListener(topics = RegistrationSuccessfulEvent.TOPIC)
    public void handleRegistrationAttempt(RegistrationSuccessfulEvent event) {
        log.info("Received event in topic {} - {}", RegistrationSuccessfulEvent.TOPIC, event);
        userService.createUser(event);

    }

}
