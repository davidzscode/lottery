package com.qihang.controller.order.admin.lottery;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.order.admin.lottery.dto.*;
import com.qihang.controller.order.admin.lottery.vo.LotteryOrderQueryVO;
import com.qihang.controller.order.app.lottery.dto.LotteryOrderDTO;
import com.qihang.controller.order.app.lottery.vo.LotteryOrderVO;
import com.qihang.service.order.ILotteryOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/admin/lottery/order")
@Api(tags = "admin 彩票订单接口集合")
public class AdminLotteryOrderController {
    
    @Resource
    private ILotteryOrderService lotteryOrderService;
    
    @Resource
    private HttpServletRequest request;
    
    
    @PostMapping("/list")
    @ApiOperation("购彩订单记录接口")
    public CommonListVO<LotteryOrderQueryVO> getLotteryOrderPage(@RequestBody @Valid LotteryOrderQueryDTO lotteryOrderQuery) {
        return lotteryOrderService.getAdminLotteryOrderPage(lotteryOrderQuery);
    }
    
    @PostMapping("/lotteryOrderList")
    @ApiOperation("购彩订单记录接口(同/user)")
    public CommonListVO<LotteryOrderVO> lotteryOrderList(@RequestBody @Valid LotteryOrderDTO lotteryOrder) {
        return lotteryOrderService.getLotteryOrderPage(lotteryOrder, lotteryOrder.getUserId());
    }
    
    @PutMapping("/ticketing")
    @ApiOperation("出票审核接口")
    public BaseVO ticketing(@RequestBody @Valid TicketingDTO ticketing) {
        return lotteryOrderService.ticketing(ticketing, null);
    }
    
    @PutMapping("/award")
    @ApiOperation("派奖接口")
    public BaseVO award(@RequestBody @Valid AwardDTO award) {
        return lotteryOrderService.award(award, null);
    }
    
    @PutMapping("/retreat/{id}")
    @ApiOperation("退票接口")
    public BaseVO retreat(@PathVariable("id") Integer id) {
        return lotteryOrderService.retreat(id);
    }
    
    
    @PutMapping("/actual")
    @ApiOperation("修改票据接口")
    public BaseVO actualVote(@RequestBody @Valid ActualVoteDTO actualVoteDTO) {
        return lotteryOrderService.actualVote(actualVoteDTO);
    }
    
    @PostMapping("/clear")
    @ApiOperation("清理流水接口")
    public BaseVO clearFlow(@RequestBody @Valid OrderFlowWaterDTO orderFlowWater) {
        return lotteryOrderService.clearFlow(orderFlowWater, request.getHeader("x-user"));
    }
}
