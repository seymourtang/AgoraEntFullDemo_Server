package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class VoiceRoomTypeMismatchException extends VoiceRoomException {

    public VoiceRoomTypeMismatchException(String message) {
        super(ErrorCodeConstants.voiceRoomTypeMismatch, message, HttpStatus.BAD_REQUEST);
    }
}
