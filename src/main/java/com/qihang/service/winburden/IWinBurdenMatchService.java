package com.qihang.service.winburden;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.winburden.vo.WinBurdenVO;
import com.qihang.domain.winburden.WinBurdenMatchDO;

/**
 * @author qihang
 * @since 2023-03-30
 */
public interface IWinBurdenMatchService extends IService<WinBurdenMatchDO> {
    /**
     * 胜负彩比赛列表
     *
     * @return
     */
    CommonListVO<WinBurdenVO> winBurdenMatchList();


    /**
     * 计算 组 注 预测金额
     *
     * @param ballCalculation
     * @return
     */
    BallCalculationVO calculation(BallCalculationDTO ballCalculation);

    /**
     * 足球14出奖计算
     *
     * @return
     */
    BaseVO victoryDefeatAward();

    /**
     * 任九出奖计算
     *
     * @return
     */
    BaseVO renJiuAward();
}
