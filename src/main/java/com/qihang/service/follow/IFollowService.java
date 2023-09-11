package com.qihang.service.follow;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.user.app.vo.UserVO;
import com.qihang.domain.follow.FollowDO;

/**
 * @author qihang
 * @since 2022-11-12
 */
public interface IFollowService extends IService<FollowDO> {
    /**
     * 添加关注关系
     *
     * @param userId
     * @param targetId
     * @param type
     * @return
     */
    BaseVO addRelationship(Integer userId, Integer targetId, String type);

    /**
     * 根据类型查询关注和粉丝列表
     *
     * @param type
     * @param userId
     * @return
     */
    CommonListVO<UserVO> list(String type, Integer userId);
}
