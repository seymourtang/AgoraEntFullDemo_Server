package com.md.service.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRealNameAuthForm {

    @Length(min = 2, max = 15, message = "userName length must be between 2 and 15")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$", message = "userName format is incorrect")
    @NotBlank(message = "userName cannot be empty")
    private String realName;

    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2]\\d)|10|20|30|31)\\d{3}[0-9Xx]$", message = "idCard format is incorrect")
    @Length(min = 18, max = 18, message = "idCard length must be 18")
    @NotBlank(message = "idCard cannot be empty")
    private String idCard;
}
