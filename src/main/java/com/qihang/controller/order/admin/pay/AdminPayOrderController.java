package com.qihang.controller.order.admin.pay;


import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.order.admin.pay.dto.PayOrderQueryDTO;
import com.qihang.controller.order.admin.pay.vo.PayOrderQueryVO;
import com.qihang.controller.order.app.pay.dto.PayOrderDTO;
import com.qihang.controller.order.app.pay.dto.PayOrderListDTO;
import com.qihang.controller.order.app.pay.vo.GetPayOrderListVO;
import com.qihang.controller.order.app.pay.vo.PayOrderListVO;
import com.qihang.controller.order.app.pay.vo.PayOrderVO;
import com.qihang.service.order.IPayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/admin/pay/order")
@Api(tags = "admin 支付订单接口集合")
public class AdminPayOrderController {
    
    @Resource
    private IPayOrderService payOrderService;
    
    @PostMapping("/list")
    @ApiOperation("支付订单记录接口")
    public CommonListVO<PayOrderQueryVO> getPayOrderPage(@RequestBody @Valid PayOrderQueryDTO payOrderQuery) {
        return payOrderService.getAdminPayOrderPage(payOrderQuery);
    }
    
    @PostMapping("/payOrderList")
    @ApiOperation("支付订单记录接口(同/user)")
    public PayOrderListVO<PayOrderVO> list(@RequestBody @Valid PayOrderDTO payOrder) {
        return payOrderService.list(payOrder, payOrder.getUserId());
    }
    
    @PostMapping("/getPayOrderList")
    @ApiOperation("分类明细列表接口")
    public CommonListVO<GetPayOrderListVO> getPayOrderList(@RequestBody @Valid PayOrderListDTO payOrderListDTO) {
        return payOrderService.getPayOrderList(payOrderListDTO, null);
    }
}
