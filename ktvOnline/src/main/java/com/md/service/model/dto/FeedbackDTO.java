package com.md.service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class FeedbackDTO {
    private String userNo;

    private Map<Integer, String> screenshotURLs;

    private List<String> tags;

    private String description;

    private String logURL;
}
