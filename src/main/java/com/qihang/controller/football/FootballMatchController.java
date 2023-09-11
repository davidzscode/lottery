package com.qihang.controller.football;

import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.football.vo.FootballVO;
import com.qihang.service.football.IFootballMatchService;
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
@Api(tags = "足彩")
@RequestMapping("/football")
public class FootballMatchController {

    @Resource
    private IFootballMatchService footballWinEvenLoseService;

    @GetMapping("/list")
    @ApiOperation("足球比赛列表接口")
    public CommonListVO<FootballVO> list() {
        return footballWinEvenLoseService.footballMatchList();
    }

    @PostMapping("/calculation")
    @ApiOperation("计算 组 注 预测金额接口")
    public BallCalculationVO calculation(@RequestBody BallCalculationDTO ballCalculation) {
        return footballWinEvenLoseService.calculation(ballCalculation);
    }
}
