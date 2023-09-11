package com.qihang.controller.basketball;

import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.basketball.vo.BasketballVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.service.basketball.IBasketballMatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-06 12:33
 */
@RestController
@Api(tags = "篮彩")
@RequestMapping("/basketball")
public class BasketballMatchController {

    @Resource
    private IBasketballMatchService basketballMatchService;

    @GetMapping("/list")
    @ApiOperation("足球比赛列表接口")
    public CommonListVO<BasketballVO> list() {
        return basketballMatchService.basketballMatchList();
    }


    @PostMapping("/calculation")
    @ApiOperation("计算 组 注 预测金额接口")
    public BallCalculationVO calculation(@RequestBody BallCalculationDTO ballCalculation) {
        return basketballMatchService.calculation(ballCalculation);
    }
}
