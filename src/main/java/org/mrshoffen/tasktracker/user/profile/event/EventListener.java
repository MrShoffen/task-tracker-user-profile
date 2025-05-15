package org.mrshoffen.tasktracker.user.profile.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.creds.EmailUpdatedSuccessEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.creds.PasswordUpdatedEvent;
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

    @KafkaListener(topics = PasswordUpdatedEvent.TOPIC)
    public void handlePasswordUpdated(PasswordUpdatedEvent event) {
        log.info("Received event in topic {} - {}", PasswordUpdatedEvent.TOPIC, event);
        userService.updateUserPassword(event.getUserId(), event.getNewHashedPassword());
    }

    @KafkaListener(topics = EmailUpdatedSuccessEvent.TOPIC)
    public void handleEmailUpdated(EmailUpdatedSuccessEvent event) {
        log.info("Received event in topic {} - {}", EmailUpdatedSuccessEvent.TOPIC, event);
        userService.updateUserEmail(event.getUserId(), event.getNewEmail());
    }

}
