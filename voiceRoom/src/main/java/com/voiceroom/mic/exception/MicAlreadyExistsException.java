package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicAlreadyExistsException extends VoiceRoomException{
    public MicAlreadyExistsException(String message) {
        super(ErrorCodeConstants.micInitAlreadyExists, message, HttpStatus.ALREADY_REPORTED);
    }
}
