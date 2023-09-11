package com.qihang.service.basketball;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.basketball.vo.BasketballVO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.domain.basketball.BasketballMatchDO;


/**
 * @author qihang
 * @since 2022-10-05
 */
public interface IBasketballMatchService extends IService<BasketballMatchDO> {

    /**
     * 足彩比赛列表
     *
     * @return
     */
    CommonListVO<BasketballVO> basketballMatchList();



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
