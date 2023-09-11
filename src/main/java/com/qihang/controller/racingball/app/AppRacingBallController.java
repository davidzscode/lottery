package com.qihang.controller.racingball.app;


import com.alipay.api.AlipayApiException;
import com.qihang.common.util.log.LogUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.mapper.user.UserMapper;
import com.qihang.service.racingball.IRacingBallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author qihang
 * @since 2022-10-24
 */
@RestController
@RequestMapping("/app/racing")
public class AppRacingBallController {
    
    @Resource
    private HttpServletRequest request;
    
    @Resource
    private IRacingBallService racingBallService;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private LogUtil logUtil;
    
    @PostMapping("/order/create")
    @ApiOperation("竞球下单接口")
    public BaseVO create(@RequestBody @Valid BallCalculationDTO ballCalculation) throws AlipayApiException {
        UserDO userDO = userMapper.selectById(Integer.valueOf(request.getAttribute("User-ID").toString()));
        if (!userDO.getIsAgent().equals(IsAgentEnum.User.getKey())) {
            return new BaseVO(false, ErrorCodeEnum.E110.getKey(), ErrorCodeEnum.E110.getValue());
        }
        LotteryOrderTypeEnum lotteryOrderTypeEnum = LotteryOrderTypeEnum.valueOFS(ballCalculation.getType());
        logUtil.record(lotteryOrderTypeEnum.getValue() + "下单,下单金额【" + BigDecimal.valueOf(ballCalculation.getNotes() * 2 * ballCalculation.getMultiple()) + "】");
        return racingBallService.createOrder(ballCalculation, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
