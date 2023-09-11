package com.qihang.controller.withdrawal.admin;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.withdrawal.admin.dto.ExamineDTO;
import com.qihang.controller.withdrawal.admin.dto.WithdrawalQueryDTO;
import com.qihang.controller.withdrawal.admin.vo.WithdrawalQueryVO;
import com.qihang.service.withdrawal.IWithdrawalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/admin/withdrawal")
@Api(tags = "admin 提现接口")
public class AdminWithdrawalController {

    @Resource
    private IWithdrawalService withdrawalService;

    @PutMapping("/examine")
    @ApiOperation("提现审核接口")
    public BaseVO examine(@RequestBody @Valid ExamineDTO examineDTO) {
        return withdrawalService.examine(examineDTO);
    }

    @PostMapping("/list")
    @ApiOperation("提现列表接口")
    public CommonListVO<WithdrawalQueryVO> pageList(@RequestBody @Valid WithdrawalQueryDTO withdrawalQuery) {
        return withdrawalService.pageList(withdrawalQuery);
    }
}
