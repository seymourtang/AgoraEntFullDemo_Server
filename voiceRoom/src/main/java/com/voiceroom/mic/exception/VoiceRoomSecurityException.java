package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class VoiceRoomSecurityException extends VoiceRoomException {

    public VoiceRoomSecurityException(String message) {
        super(ErrorCodeConstants.roomUnSupportedOperation, message,
                HttpStatus.UNAUTHORIZED);
    }
}
