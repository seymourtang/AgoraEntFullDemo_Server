package com.md.service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CacheDTO {

    private LocalDateTime time;

    private Object object;
}
