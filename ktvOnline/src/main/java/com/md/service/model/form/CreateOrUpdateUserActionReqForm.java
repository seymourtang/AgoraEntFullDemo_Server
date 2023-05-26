package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateUserActionReqForm {
    @ApiModelProperty("用户行为")
    @NotBlank(message = "action cannot be empty")
    private String action;
}
