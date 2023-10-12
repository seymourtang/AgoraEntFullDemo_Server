package com.md.service.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MetaDataDTO {
    private Integer key;

    private String value;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
