package com.qihang.controller.winburden;


import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.winburden.vo.WinBurdenVO;
import com.qihang.service.winburden.IWinBurdenMatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2023-03-30
 */
@RestController
@Api(tags = "胜负彩")
@RequestMapping("/victory")
public class WinBurdenMatchController {

    @Resource
    private IWinBurdenMatchService winBurdenMatchService;

    @GetMapping("/list")
    @ApiOperation("胜负彩比赛列表接口")
    public CommonListVO<WinBurdenVO> list() {
        return winBurdenMatchService.winBurdenMatchList();
    }

    @PostMapping("/calculation")
    @ApiOperation("计算 组 注 预测金额接口")
    public BallCalculationVO calculation(@RequestBody BallCalculationDTO ballCalculation) {
        return winBurdenMatchService.calculation(ballCalculation);
    }
}
