package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class CreateRoomFailedException extends VoiceRoomException {

    public CreateRoomFailedException(String message) {
        super(ErrorCodeConstants.createChatroomFailed, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
