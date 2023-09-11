package com.qihang.controller.order.app.lottery;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.order.admin.lottery.dto.AwardDTO;
import com.qihang.controller.order.admin.lottery.dto.TicketingDTO;
import com.qihang.controller.order.app.lottery.dto.LotteryOrderDTO;
import com.qihang.controller.order.app.lottery.vo.DeleteOrderVO;
import com.qihang.controller.order.app.lottery.vo.LotteryOrderVO;
import com.qihang.controller.order.app.lottery.vo.YesterdayCentreOrderVO;
import com.qihang.service.order.ILotteryOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/app/lottery/order")
@Api(tags = "app 彩票订单接口集合")
public class AppLotteryOrderController {
    
    @Resource
    private ILotteryOrderService lotteryOrderService;
    
    @Resource
    private ServletRequest request;
    
    @PostMapping("/list")
    @ApiOperation("购彩订单记录接口")
    public CommonListVO<LotteryOrderVO> getLotteryOrderPage(@RequestBody @Valid LotteryOrderDTO lotteryOrder) {
        return lotteryOrderService.getLotteryOrderPage(lotteryOrder, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @GetMapping("/get/{id}")
    @ApiOperation("根据id订单订单记录接口")
    public LotteryOrderVO getLotteryOrderById(@PathVariable("id") Integer id) {
        return lotteryOrderService.getLotteryOrderById(id, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @GetMapping("/centre")
    @ApiOperation("获取昨日下单中奖的订单提示信息")
    public CommonListVO<YesterdayCentreOrderVO> centre() {
        return lotteryOrderService.centre();
    }
    
    @PostMapping("/deleteOrder")
    @ApiOperation("删除订单")
    public BaseVO deleteOrder(@RequestBody @Valid DeleteOrderVO lotteryOrder) {
        return lotteryOrderService.deleteOrder(lotteryOrder);
    }
    
    @PostMapping("/award")
    @ApiOperation("店主派奖接口")
    public BaseVO award(@RequestBody @Valid AwardDTO award) {
        return lotteryOrderService.award(award, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/ticketing")
    @ApiOperation("店主出票接口")
    public BaseVO ticketing(@RequestBody @Valid TicketingDTO ticketing) {
        return lotteryOrderService.ticketing(ticketing, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
