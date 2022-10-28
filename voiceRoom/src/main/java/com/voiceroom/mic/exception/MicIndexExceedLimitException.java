package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicIndexExceedLimitException extends VoiceRoomException {

    public MicIndexExceedLimitException(String message) {
        super(ErrorCodeConstants.micIndexExceedLimitError, message, HttpStatus.BAD_REQUEST);
    }
}
