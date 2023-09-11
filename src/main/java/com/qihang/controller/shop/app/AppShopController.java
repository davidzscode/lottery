package com.qihang.controller.shop.app;


import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.shop.app.dto.ShopDTO;
import com.qihang.controller.shop.app.vo.ShopVO;
import com.qihang.service.shop.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2022-11-13
 */
@RestController
@RequestMapping("/app/shop")
@Api(tags = "APP - 店铺")
public class AppShopController {
    @Resource
    private IShopService shopService;

    @PostMapping("/list")
    @ApiOperation("店铺列表接口")
    public CommonListVO<ShopVO> shopList(@RequestBody ShopDTO shop) {
        return shopService.shopList(shop);
    }

}
