package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends VoiceRoomException {

    public UserNotFoundException() {
        super(ErrorCodeConstants.userNotFound, "user must not be null", HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(String message) {
        super(ErrorCodeConstants.userNotFound, message, HttpStatus.NOT_FOUND);
    }
}
