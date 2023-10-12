package com.md.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.dto.FeedbackDTO;
import com.md.service.model.entity.Feedback;
import com.md.service.model.entity.Users;
import com.md.service.repository.FeedbackMapper;
import com.md.service.service.FeedbackService;
import com.md.service.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Resource
    private UsersService usersService;

    @Override
    public void insertFeedback(FeedbackDTO feedback) {
        Users user = usersService.getUserByNo(feedback.getUserNo());
        Feedback toInsert = Feedback.builder()
                .userNo(feedback.getUserNo())
                .name(user.getName())
                .tags(JSON.toJSONString(feedback.getTags()))
                .screenshotURLs(JSON.toJSONString(feedback.getScreenshotURLs()))
                .description(feedback.getDescription())
                .logURL(feedback.getLogURL())
                .build();
        baseMapper.insertFeedback(toInsert);
    }
}
