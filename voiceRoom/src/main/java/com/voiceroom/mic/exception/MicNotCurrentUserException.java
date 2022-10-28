package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicNotCurrentUserException extends VoiceRoomException {

    public MicNotCurrentUserException() {
        super(ErrorCodeConstants.micNotCurrentUser, "mic not current user",
                HttpStatus.BAD_REQUEST);
    }
}
