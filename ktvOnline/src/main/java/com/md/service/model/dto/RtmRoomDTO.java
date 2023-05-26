package com.md.service.model.dto;

import lombok.Data;

@Data
public class RtmRoomDTO<T>{

    private String roomNo;

    private Integer status;

    private String userNo;

    private Integer seat;

    private T data;

    private String code = "room";

    private Long time = System.currentTimeMillis();

}
