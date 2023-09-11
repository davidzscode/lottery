package com.qihang.service.permutation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.permutation.app.dto.PlaceOrderDTO;
import com.qihang.controller.permutation.app.vo.PermutationRecordVO;
import com.qihang.domain.permutation.PermutationAwardDO;
import com.qihang.domain.permutation.PermutationDO;

import java.util.List;

/**
 * @author qihang
 * @since 2022-10-10
 */
public interface IPermutationService extends IService<PermutationDO> {

    /**
     * 排序 下单接口
     *
     * @param placeList
     * @param userId
     * @param userId
     * @param type
     * @return
     */
    BaseVO placeOrder(List<PlaceOrderDTO> placeList, Integer userId, String type);


    /**
     * 出奖记录
     *
     * @param type
     * @return
     */
    CommonListVO<PermutationRecordVO> record(String type);


    /**
     * 计算用户中奖结果
     *
     * @param permutationAward
     * @return
     */
    BaseVO calculation(PermutationAwardDO permutationAward);
}
