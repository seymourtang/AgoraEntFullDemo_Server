package com.md.service.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class UploadFeedbackForm {
    @NotNull(message = "screenshotURLs cannot be empty")
    private Map<Integer, String> screenshotURLs;

    @NotNull
    private List<String> tags;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotBlank(message = "logURL cannot be empty")
    private String logURL;
}
