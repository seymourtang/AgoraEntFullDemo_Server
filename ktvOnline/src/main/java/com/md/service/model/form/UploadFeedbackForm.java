package com.md.service.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Data
public class UploadFeedbackForm {
    @NotBlank(message = "screenshotURLs cannot be empty")
    private Map<Integer, String> screenshotURLs;

    @NotBlank(message = "tags cannot be empty")
    private List<String> tags;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotBlank(message = "logURL cannot be empty")
    private String logURL;
}
