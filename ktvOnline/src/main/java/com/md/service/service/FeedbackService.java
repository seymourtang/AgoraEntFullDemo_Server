package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.FeedbackDTO;
import com.md.service.model.entity.Feedback;

public interface FeedbackService extends IService<Feedback> {

    void insertFeedback(FeedbackDTO feedback);
}
