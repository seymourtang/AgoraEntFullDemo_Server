package com.md.service.model.dto;

import lombok.Data;

@Data
public class RtmSongDTO {

    private String roomNo;

    private String songNo;

    private Integer status;

    private String code = "song";

    private Long time = System.currentTimeMillis();

}
