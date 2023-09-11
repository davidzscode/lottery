package com.qihang.service.follow;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.user.app.vo.UserVO;
import com.qihang.domain.follow.FollowDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.follow.FollowEnum;
import com.qihang.mapper.follow.FollowMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qihang
 * @since 2022-11-12
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, FollowDO> implements IFollowService {

    @Resource
    private FollowMapper followMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TenantIgnore
    public BaseVO addRelationship(Integer userId, Integer targetId, String type) {
        if (ObjectUtil.equal(userId, targetId)) {
            return new BaseVO(false, ErrorCodeEnum.E079.getKey(), ErrorCodeEnum.E079.getValue());
        }
        FollowDO follow = followMapper.selectOne(new QueryWrapper<FollowDO>().lambda().eq(FollowDO::getUserId, userId).eq(FollowDO::getTargetId, targetId));
        UserDO targetUser = userMapper.selectById(targetId);
        UserDO user = userMapper.selectById(userId);
        if (ObjectUtil.isNull(follow)) {
            //为空必定是关注 需要添加数据
            FollowDO followDO = new FollowDO();
            followDO.setUserId(userId);
            followDO.setTargetId(targetId);
            followDO.setType(type);
            followDO.setCreateTime(new Date());
            followDO.setUpdateTime(new Date());
            followMapper.insert(followDO);
        }
        //不为空判断修改
        if (ObjectUtil.isNotNull(follow)) {
            follow.setType(type);
            followMapper.updateById(follow);
        }
        if (type.equals(FollowEnum.FOLLOW.getKey()) || ObjectUtil.isNull(follow)) {
            //添加粉丝数
            targetUser.setFans(targetUser.getFans() + 1);
            userMapper.updateById(targetUser);
            //添加关注数
            user.setFollow(user.getFollow() + 1);
            userMapper.updateById(user);
        } else if (type.equals(FollowEnum.CANCEL.getKey())) {
            //减少粉丝数
            targetUser.setFans(targetUser.getFans() - 1);
            userMapper.updateById(targetUser);
            //减少关注数
            user.setFollow(user.getFollow() - 1);
            userMapper.updateById(user);
        }
        return new BaseVO();
    }

    @Override
    @TenantIgnore
    public CommonListVO<UserVO> list(String type, Integer userId) {
        CommonListVO<UserVO> commonList = new CommonListVO<>();
        List<UserVO> list = new ArrayList<>();
        List<FollowDO> followList;
        if (type.equals("0")) {
            //关注
            followList = followMapper.selectList(new QueryWrapper<FollowDO>().lambda().eq(FollowDO::getUserId, userId).eq(FollowDO::getType, FollowEnum.FOLLOW.getKey()));
            for (FollowDO followDO : followList) {
                UserVO user = new UserVO();
                UserDO userDO = userMapper.selectById(followDO.getTargetId());
                BeanUtils.copyProperties(userDO, user);
                list.add(user);
            }
        } else if (type.equals("1")) {
            //粉丝
            followList = followMapper.selectList(new QueryWrapper<FollowDO>().lambda().eq(FollowDO::getTargetId, userId).eq(FollowDO::getType, FollowEnum.FOLLOW.getKey()));
            for (FollowDO followDO : followList) {
                UserVO user = new UserVO();
                UserDO userDO = userMapper.selectById(followDO.getUserId());
                BeanUtils.copyProperties(userDO, user);
                list.add(user);
            }
        }
        commonList.setVoList(list);
        return commonList;
    }
}
