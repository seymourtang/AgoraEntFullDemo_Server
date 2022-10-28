package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicInitException extends VoiceRoomException {

    public MicInitException() {
        super(ErrorCodeConstants.micInitError, "mic init error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
