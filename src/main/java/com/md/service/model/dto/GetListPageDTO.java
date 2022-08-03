package com.md.service.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class GetListPageDTO  {

    private String name;

    private String type;

    private Page page;
}
