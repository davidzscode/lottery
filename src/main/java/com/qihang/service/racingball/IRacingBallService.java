package com.qihang.service.racingball;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.domain.racingball.RacingBallDO;

/**
 * @author qihang
 * @since 2022-10-24
 */
public interface IRacingBallService extends IService<RacingBallDO> {
    /**
     * 竞球下注
     *
     * @param ballCalculation
     * @param userId
     * @return
     * @throws AlipayApiException
     */
    BaseVO createOrder(BallCalculationDTO ballCalculation, Integer userId) throws AlipayApiException;

}
