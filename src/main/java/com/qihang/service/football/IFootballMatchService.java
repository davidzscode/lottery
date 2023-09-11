package com.qihang.service.football;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.football.vo.FootballVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.domain.football.FootballMatchDO;


/**
 * 足彩 胜负平表 服务类
 *
 * @author qihang
 * @since 2022-10-05
 */
public interface IFootballMatchService extends IService<FootballMatchDO> {

    /**
     * 足彩比赛列表
     *
     * @return
     */
    CommonListVO<FootballVO> footballMatchList();

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
