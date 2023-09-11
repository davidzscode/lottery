package com.qihang.controller.statistics.app;

import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.statistics.app.dto.CountShopPriceDTO;
import com.qihang.controller.statistics.app.vo.CountShopPriceVO;
import com.qihang.controller.statistics.app.vo.YesterdayWinningListVO;
import com.qihang.service.statistics.IStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 统计接口
 * @author lihv
 * @since 2023/7/29
 */
@Api(tags = "APP - 统计")
@RestController
@RequestMapping("/app/statistics")
public class AppStatisticsController {
    
    @Resource
    private IStatisticsService statisticsService;
    @Resource
    private HttpServletRequest request;
    
    @PostMapping("/countShopPrice")
    @ApiOperation("统计店铺金额")
    public CountShopPriceVO countShopPrice(@RequestBody @Valid CountShopPriceDTO countShopPriceDTO) {
        return statisticsService.countShopPrice(countShopPriceDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @GetMapping("/yesterdayWinningList")
    @ApiOperation("昨日中奖榜单")
    public CommonListVO<YesterdayWinningListVO> winningList() {
        return statisticsService.yesterdayWinningList();
    }
}
