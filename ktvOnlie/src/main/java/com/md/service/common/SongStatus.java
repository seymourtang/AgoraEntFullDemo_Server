package com.md.service.common;

import lombok.Getter;

@Getter
public enum SongStatus {

    and_so(1, "点歌"),
    switch_song(2, "切歌"),
    over(3, "唱完"),
    chorus(4, "合唱"),
    begin(5, "开始唱歌"),



    ;


    private final Integer code;

    private final String message;

    SongStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
