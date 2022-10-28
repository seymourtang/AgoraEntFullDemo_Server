package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicApplyRecordNotFoundException extends VoiceRoomException {

    public MicApplyRecordNotFoundException() {
        super(ErrorCodeConstants.micApplyRecordNotFoundError, "mic apply record not found",
                HttpStatus.NOT_FOUND);
    }
}
