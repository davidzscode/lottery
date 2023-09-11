package com.qihang.controller.statistics;

import com.qihang.controller.statistics.app.dto.CountShopPriceDTO;
import com.qihang.controller.statistics.app.vo.CountShopPriceVO;
import com.qihang.controller.statistics.vo.StatisticsVO;
import com.qihang.service.statistics.IStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-06 13:47
 */
@Api(tags = "后台 - 统计")
@RestController
@RequestMapping("/admin/statistics")
public class AdminStatisticsController {
    
    @Resource
    private IStatisticsService statisticsService;
    
    @GetMapping("/get")
    @ApiOperation("统计接口")
    public StatisticsVO calculation() {
        return statisticsService.calculation();
    }
    
    @PostMapping("/countShopPrice")
    @ApiOperation("统计店铺金额")
    public CountShopPriceVO countShopPrice(@RequestBody @Valid CountShopPriceDTO countShopPriceDTO) {
        return statisticsService.countShopPrice(countShopPriceDTO, null);
    }
}
