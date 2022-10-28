package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicApplyRepeatException extends VoiceRoomException {

    public MicApplyRepeatException() {
        super(ErrorCodeConstants.micRepeatApplyError, "addMicApply repeat error", HttpStatus.BAD_REQUEST);
    }
}
