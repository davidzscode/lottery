package com.qihang.controller.shop.admin;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.shop.admin.dto.*;
import com.qihang.controller.shop.admin.vo.AdminShopVO;
import com.qihang.controller.shop.admin.vo.ShopDetailVO;
import com.qihang.service.shop.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-11-13
 */
@RestController
@RequestMapping("/admin/shop")
@Api(tags = "后台 - 店铺")
public class AdminShopController {
    @Resource
    private IShopService shopService;

    @Resource
    private ServletRequest request;

    @PostMapping("/list")
    @ApiOperation("店铺列表接口")
    public CommonListVO<AdminShopVO> shopList(@RequestBody AdminShopDTO adminShop) {
        return shopService.adminShopList(adminShop);
    }

    @PutMapping("/update/line/{id}/{type}")
    @ApiOperation("福彩上下架接口")
    public BaseVO updateLine(@PathVariable("id") Integer id, @PathVariable("type") String type) {
        return shopService.updateLine(id, type);
    }

    @PostMapping("/add")
    @ApiOperation("添加店铺接口")
    public BaseVO add(@RequestBody AddShopDTO addShop) {
        return shopService.add(addShop, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }

    @PostMapping("/recharge")
    @ApiOperation("店铺充值")
    public BaseVO recharge(@RequestBody ShopRechargeDTO shopRecharge) {
        return shopService.recharge(shopRecharge);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除店铺")
    public BaseVO delete(@PathVariable("id") Integer id) {
        return shopService.delete(id);
    }

    @PostMapping("/editShopRate")
    @ApiOperation("设置店铺扣款比例")
    public BaseVO editShopRate(@RequestBody EditShopRateDTO dto) {
        return shopService.editShopRate(dto);
    }

    @PostMapping("/shopDetail")
    @ApiOperation("获取店铺详情")
    public ShopDetailVO shopDetail(@RequestBody @Valid ShopDetailDTO dto) {
        return shopService.shopDetail(dto);
    }

    @PostMapping("/editShopLine")
    @ApiOperation("店铺上架下架")
    public BaseVO editShopLine(@RequestBody @Valid EditShopLineDTO dto) {
        return shopService.editShopLine(dto);
    }

}
