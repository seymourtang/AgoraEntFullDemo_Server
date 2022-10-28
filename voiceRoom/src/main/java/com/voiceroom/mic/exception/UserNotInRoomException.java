package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class UserNotInRoomException extends VoiceRoomException {

    public UserNotInRoomException() {
        super(ErrorCodeConstants.userNotInRoomError, "user not in voice room", HttpStatus.FORBIDDEN);
    }

    public UserNotInRoomException(String message) {
        super(ErrorCodeConstants.userNotInRoomError, message, HttpStatus.FORBIDDEN);
    }
}
