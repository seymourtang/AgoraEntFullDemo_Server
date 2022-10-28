package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicApplyException extends VoiceRoomException {

    public MicApplyException() {
        super(ErrorCodeConstants.micApplyError, "addMicApply error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
