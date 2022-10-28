package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicNotBelongYouException extends VoiceRoomException {

    public MicNotBelongYouException() {
        super(ErrorCodeConstants.micNotBelongYouError, "mic index not belong you",
                HttpStatus.UNAUTHORIZED);
    }
}
