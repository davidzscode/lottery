package com.qihang.service.ballgame;

import com.alibaba.fastjson.JSON;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.ballgame.admin.dto.AdminBallGameDTO;
import com.qihang.controller.ballgame.admin.vo.AdminBallGameVO;
import com.qihang.controller.ballgame.app.vo.BallGameVO;
import com.alibaba.fastjson.JSONObject;


/**
 * @author qihang
 * @since 2022-10-03
 */
public interface IBallGameService {
    /**
     * 福彩列表
     */
    CommonListVO<BallGameVO> list();
    
    /**
     * 后台数据
     */
    CommonListVO<AdminBallGameVO> adminList();
    
    
    /**
     * 上下架
     * @param id
     * @param type
     */
    BaseVO updateLine(Integer id, String type);
    
    BaseVO updateLine(AdminBallGameDTO dto);

    /**
    * 获取版本
    * */
    JSONObject getVersion();


}
