package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import com.md.service.model.dto.RoomUserInfoDTO;
import com.md.service.model.entity.RoomUsers;
import com.md.service.model.entity.Users;
import com.md.service.repository.RoomUsersMapper;
import com.md.service.service.RoomUsersService;
import com.md.service.service.UsersService;
import com.md.service.utils.AgoraentertainmentUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 房间用户 服务实现类
 * </p>
 */
@Service
public class RoomUsersServiceImpl extends ServiceImpl<RoomUsersMapper, RoomUsers> implements RoomUsersService {

    @Resource
    private UsersService usersService;

    @Resource
    private AgoraentertainmentUtils agoraentertainmentUtils;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void joinRoom(String roomNo, Integer userId, Integer seat, Integer joinSing) {
        RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>().
                eq(RoomUsers::getRoomNo, roomNo).eq(RoomUsers::getUserId, userId));
        if (roomUsers == null) {
            roomUsers = new RoomUsers();
            roomUsers.setRoomNo(roomNo);
            roomUsers.setUserId(userId);
            roomUsers.setOnSeat(seat);
            roomUsers.setJoinSing(joinSing);
            baseMapper.insert(roomUsers);
        } else {
            if (seat != -1) {
                //房主不能动
                if(roomUsers.getOnSeat().equals(0)){
                    return;
                }
                roomUsers.setOnSeat(seat);
            }
            if (joinSing != 0) {
                roomUsers.setJoinSing(joinSing);
            }
            baseMapper.updateById(roomUsers);
        }
    }

    @Override
    public boolean checkStatus(String roomNo, Integer userId, Integer seat, Integer joinSing) {
        Boolean result = false;
        if (roomNo == null) {
            return false;
        }
        if (seat != 0) {
            RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>()
                    .eq(RoomUsers::getRoomNo, roomNo)
                    .eq(RoomUsers::getOnSeat, seat));
            //房间座位无人
            if (roomUsers == null) {
                return true;
            }else{
                throw new BaseException(ErrorCodeEnum.seat_was);
            }
        }
        if (joinSing != 0) {
            RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>()
                    .eq(RoomUsers::getRoomNo, roomNo).eq(RoomUsers::getUserId, userId));
            //在座位上 可以合唱
            if (roomUsers != null && roomUsers.getOnSeat() != 0) {
                return true;
            }
        }
        return result;
    }

    @Override
    public void outSeat(String roomNo, Integer userId,String userNo) {
        RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>().
                eq(RoomUsers::getRoomNo, roomNo).eq(RoomUsers::getUserId, userId));
        if(roomUsers != null){
            String redisKey = "reviewVoice_"+ userNo+ ":" + roomNo + ":" + roomUsers.getOnSeat();
            redisTemplate.delete(redisKey);
            //房主不能动
            if(roomUsers.getOnSeat().equals(0)){
                return;
            }
            roomUsers.setOnSeat(-1);
            baseMapper.updateById(roomUsers);
        }
    }

    @Override
    public List<RoomUserInfoDTO> roomUserInfoDTOList(String roomNo,String masterNo) {
        List<RoomUsers> roomUsers = baseMapper.selectList(new LambdaQueryWrapper<RoomUsers>().
                eq(RoomUsers::getRoomNo, roomNo));
        List<RoomUserInfoDTO> result = new ArrayList<>();
        roomUsers.forEach(e ->{
            if(e.getOnSeat() >= 0){
                RoomUserInfoDTO roomUserInfoDTO = new RoomUserInfoDTO();
                roomUserInfoDTO.setOnSeat(e.getOnSeat());
                roomUserInfoDTO.setJoinSing(e.getJoinSing());
                Users users = usersService.getById(e.getUserId());
                //用户存在 没注销
                if(users != null){
                    roomUserInfoDTO.setUserNo(users.getUserNo());
                    roomUserInfoDTO.setIsSelfMuted(e.getIsSelfMuted());
                    roomUserInfoDTO.setIsVideoMuted(e.getIsVideoMuted());
                    roomUserInfoDTO.setHeadUrl(users.getHeadUrl());
                    roomUserInfoDTO.setName(users.getName());
                    roomUserInfoDTO.setId(users.getId());
                    if(masterNo.equals(users.getUserNo())){
                        roomUserInfoDTO.setIsMaster(true);
                    }
                    result.add(roomUserInfoDTO);
                }
            }
        } );
        return result;
    }

    @Override
    public Long getRoomNum(String roomNo) {
        return baseMapper.selectCount(new LambdaQueryWrapper<RoomUsers>().eq(RoomUsers::getRoomNo,roomNo));
    }

    @Override
    public void outRoom(String roomNo, String userNo) {
        Users users = usersService.getUserByNo(userNo);
        RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>().eq(RoomUsers::getRoomNo,roomNo).
                eq(RoomUsers::getUserId,users.getId()).last("limit 1"));
        baseMapper.deleteById(roomUsers);
    }

    @Override
    public void isVideoMuted(String roomNo, String userNo) {
        Users users = usersService.getUserByNo(userNo);
        RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>().eq(RoomUsers::getRoomNo,roomNo).
                eq(RoomUsers::getUserId,users.getId()).last("limit 1"));
        if(roomUsers.getIsVideoMuted() == 0){
            roomUsers.setIsVideoMuted(1);
            try {
                agoraentertainmentUtils.openRecording(users.getId(),roomNo);
            }catch (Exception e){
                log.error("openRecording error",e);
            }
        }else{
            roomUsers.setIsVideoMuted(0);
            try {
                agoraentertainmentUtils.closeRecording(users.getId(),roomNo);
            }catch (Exception e){
                log.error("closeRecording error",e);
            }
        }
        baseMapper.updateById(roomUsers);
    }

    @Override
    public void isSelfMuted(String roomNo, String userNo) {
        Users users = usersService.getUserByNo(userNo);
        RoomUsers roomUsers = baseMapper.selectOne(new LambdaQueryWrapper<RoomUsers>().eq(RoomUsers::getRoomNo,roomNo).
                eq(RoomUsers::getUserId,users.getId()).last("limit 1"));
        if(roomUsers.getIsSelfMuted() == 0){
            roomUsers.setIsSelfMuted(1);
        }else{
            roomUsers.setIsSelfMuted(0);
        }
        baseMapper.updateById(roomUsers);
    }
}
