package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class MicStatusCannotBeModifiedException extends VoiceRoomException {

    public MicStatusCannotBeModifiedException() {
        super(ErrorCodeConstants.micStatusCannotBeModified, "mic current status cannot be modified",
                HttpStatus.FORBIDDEN);
    }
}
