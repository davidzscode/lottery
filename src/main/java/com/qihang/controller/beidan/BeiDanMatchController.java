package com.qihang.controller.beidan;


import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.beidan.vo.BeiDanVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.service.beidan.IBeiDanMatchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/beidan")
public class BeiDanMatchController {
    @Resource
    private IBeiDanMatchService beiDanMatchService;

    @GetMapping("/list")
    @ApiOperation("北单比赛列表接口")
    public CommonListVO<BeiDanVO> list() {
        return beiDanMatchService.beiDanMatchList();
    }

    @PostMapping("/calculation")
    @ApiOperation("计算 组 注 预测金额接口")
    public BallCalculationVO calculation(@RequestBody BallCalculationDTO ballCalculation) {
        return beiDanMatchService.calculation(ballCalculation);
    }
}
