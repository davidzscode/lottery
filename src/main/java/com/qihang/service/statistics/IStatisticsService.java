package com.qihang.service.statistics;

import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.statistics.app.dto.CountShopPriceDTO;
import com.qihang.controller.statistics.app.vo.CountShopPriceVO;
import com.qihang.controller.statistics.app.vo.YesterdayWinningListVO;
import com.qihang.controller.statistics.vo.StatisticsVO;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-06 13:45
 */
public interface IStatisticsService {
    
    /**
     * 统计
     */
    StatisticsVO calculation();
    
    CountShopPriceVO countShopPrice(CountShopPriceDTO countShopPriceDTO, Integer valueOf);
    
    CommonListVO<YesterdayWinningListVO> yesterdayWinningList();
}
