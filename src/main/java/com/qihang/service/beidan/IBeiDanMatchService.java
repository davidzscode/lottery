package com.qihang.service.beidan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.beidan.vo.BeiDanVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.domain.beidan.BeiDanMatchDO;

/**
 * @author qihang
 * @since 2022-11-02
 */
public interface IBeiDanMatchService extends IService<BeiDanMatchDO> {


    /**
     * 北单比赛列表
     *
     * @return
     */
    CommonListVO<BeiDanVO> beiDanMatchList();


    /**
     * 计算 组 注 预测金额
     *
     * @param ballCalculation
     * @return
     */
    BallCalculationVO calculation(BallCalculationDTO ballCalculation);


    /**
     * 出奖计算
     *
     * @return
     */
    BaseVO award();
}
