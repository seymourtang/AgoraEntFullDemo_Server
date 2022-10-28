package com.voiceroom.mic.exception;

import com.voiceroom.mic.common.constants.ErrorCodeConstants;
import org.springframework.http.HttpStatus;

public class GiftNotFoundException extends VoiceRoomException {

    public GiftNotFoundException(String message) {
        super(ErrorCodeConstants.giftNotFound, message, HttpStatus.NOT_FOUND);
    }
}
