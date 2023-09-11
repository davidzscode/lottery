package com.qihang.service.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.shop.admin.dto.*;
import com.qihang.controller.shop.admin.vo.AdminShopVO;
import com.qihang.controller.shop.admin.vo.ShopDetailVO;
import com.qihang.controller.shop.app.dto.ShopDTO;
import com.qihang.controller.shop.app.vo.ShopVO;
import com.qihang.domain.shop.ShopDO;

/**
 * @author qihang
 * @since 2022-11-13
 */
public interface IShopService extends IService<ShopDO> {
    /**
     * 根据手机号查询店铺列表
     *
     * @param shop
     */
    CommonListVO<ShopVO> shopList(ShopDTO shop);

    /**
     * 后台列表
     *
     * @param adminShop
     */
    CommonListVO<AdminShopVO> adminShopList(AdminShopDTO adminShop);


    /**
     * 上下架
     *
     * @param id
     * @param type
     */
    BaseVO updateLine(Integer id, String type);


    /**
     * 添加店铺
     *
     * @param addShop
     * @param userId
     */
    BaseVO add(AddShopDTO addShop, Integer userId);

    /**
     * 店铺充值
     *
     * @param shopRecharge
     */
    BaseVO recharge(ShopRechargeDTO shopRecharge);

    /**
     * 根据id查询店铺信息
     *
     * @param id
     */
    ShopDO getShopById(Integer id);

    /**
     * 删除店铺
     *
     * @param id
     */
    BaseVO delete(Integer id);

    BaseVO editShopRate(EditShopRateDTO dto);

    ShopDetailVO shopDetail(ShopDetailDTO dto);

    BaseVO editShopLine(EditShopLineDTO dto);

}
