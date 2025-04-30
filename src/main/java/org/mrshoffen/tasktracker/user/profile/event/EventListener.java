package org.mrshoffen.tasktracker.user.profile.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationAttemptEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationFailedEvent;
import org.mrshoffen.tasktracker.user.profile.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final UserService userService;

    //Сохраняем пользователя даже при попытке регистрации. Если будет ивент на неудачную регу - откатываем
    @KafkaListener(topics = RegistrationAttemptEvent.TOPIC)
    public void handleRegistrationAttempt(RegistrationAttemptEvent event) {
        log.info("Received event in topic {} - {}", RegistrationAttemptEvent.TOPIC, event);
        userService.createUser(event);

    }

    @KafkaListener(topics = RegistrationFailedEvent.TOPIC)
    public void handleRegistrationFail(RegistrationFailedEvent event) {
        log.info("Received event in topic {} - {}", RegistrationFailedEvent.TOPIC, event);
        userService.deleteUserById(event.getRegistrationId());

    }

}
