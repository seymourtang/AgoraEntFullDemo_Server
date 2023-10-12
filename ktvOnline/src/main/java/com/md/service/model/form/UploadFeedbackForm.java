package com.md.service.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class UploadFeedbackForm {
    @NotNull(message = "screenshotURLs cannot be empty")
    private Map<Integer, String> screenshotURLs;

    @NotNull
    private List<String> tags;

    private String description;

    private String logURL;
}
