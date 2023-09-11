package com.qihang.service.withdrawal;

import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.withdrawal.admin.dto.ExamineDTO;
import com.qihang.controller.withdrawal.admin.dto.WithdrawalQueryDTO;
import com.qihang.controller.withdrawal.admin.vo.WithdrawalQueryVO;
import com.qihang.controller.withdrawal.app.vo.RecordVO;
import com.qihang.controller.withdrawal.app.dto.WithdrawalDTO;

import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-08
 */
public interface IWithdrawalService {

    /**
     * 提现
     *
     * @param withdrawalDTO
     * @param userId
     * @return
     */
    BaseVO add(WithdrawalDTO withdrawalDTO, Integer userId);


    /**
     * 提现审核
     *
     * @param examineDTO
     * @return
     */
    BaseVO examine(ExamineDTO examineDTO);


    /**
     * 提现记录列表
     *
     * @param userId
     * @return
     */
    CommonListVO<RecordVO> list(Integer userId);


    /**
     * 后台提现列表
     *
     * @param withdrawalQuery
     * @return
     */
    CommonListVO<WithdrawalQueryVO> pageList(WithdrawalQueryDTO withdrawalQuery);


    /**
     * 店铺内提现列表
     *
     * @param withdrawalQuery
     * @return
     */

    CommonListVO<WithdrawalQueryVO> widthdrawalList(WithdrawalQueryDTO withdrawalQuery,Integer shopid);
}
