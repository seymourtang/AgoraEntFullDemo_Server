package com.md.service.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// https://help.aliyun.com/zh/id-verification/information-verification/developer-reference/vatsl9lfmbwe74iv
public class RealNameAuthDTO {
    private String requestId;

    private String message;

    // 返回码：200表示成功，其他均为失败。
    private String code;

    // 1：校验⼀致
    //
    // 2：校验不⼀致
    //
    // 3：查⽆记录
    private String result;
}
