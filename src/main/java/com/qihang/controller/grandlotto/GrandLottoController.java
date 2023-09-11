package com.qihang.controller.grandlotto;

import com.qihang.controller.grandlotto.dto.GrandLottoDTO;
import com.qihang.controller.grandlotto.vo.GrandLottoVO;
import com.qihang.service.grandlotto.IGrandLottoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-06 12:33
 */
@RestController
@Api(tags = "大乐透")
@RequestMapping("/grand")
public class GrandLottoController {

    @Resource
    private IGrandLottoService grandLottoService;

    @PostMapping("/calculation")
    @ApiOperation("计算 组 注 预测金额接口")
    public GrandLottoVO calculation(@RequestBody GrandLottoDTO grandLotto) {
        return grandLottoService.calculation(grandLotto);
    }
}
