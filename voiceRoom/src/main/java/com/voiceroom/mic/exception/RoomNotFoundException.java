package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends VoiceRoomException {

    public RoomNotFoundException(String message) {
        super(ErrorCodeConstants.roomNotFound, message, HttpStatus.NOT_FOUND);
    }
}
