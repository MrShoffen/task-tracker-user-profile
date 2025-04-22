package org.mrshoffen.tasktracker.user.profile.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
