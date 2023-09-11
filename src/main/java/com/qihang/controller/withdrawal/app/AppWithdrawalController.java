package com.qihang.controller.withdrawal.app;


import com.qihang.common.util.log.LogUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.order.app.pay.dto.PayOrderListDTO;
import com.qihang.controller.withdrawal.admin.dto.ExamineDTO;
import com.qihang.controller.withdrawal.admin.dto.WithdrawalQueryDTO;
import com.qihang.controller.withdrawal.admin.vo.WithdrawalQueryVO;
import com.qihang.controller.withdrawal.app.dto.WithdrawalDTO;
import com.qihang.controller.withdrawal.app.vo.RecordVO;
import com.qihang.service.withdrawal.IWithdrawalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/app/withdrawal")
@Api(tags = "app 提现接口")
public class AppWithdrawalController {

    @Resource
    private IWithdrawalService withdrawalService;

    @Resource
    private ServletRequest request;

    @Resource
    private LogUtil logUtil;

    @PostMapping("/add")
    @ApiOperation("提现申请接口")
    public BaseVO add(@RequestBody @Valid WithdrawalDTO withdrawalDTO) {
        logUtil.record("发起【" + withdrawalDTO.getAmount() + "】提现申请");
        return withdrawalService.add(withdrawalDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }

    @PutMapping("/examine")
    @ApiOperation("提现审核接口")
    public BaseVO examine(@RequestBody @Valid ExamineDTO examineDTO) {
        return withdrawalService.examine(examineDTO);
    }

    @GetMapping("/list")
    @ApiOperation("提现记录列表接口")
    public CommonListVO<RecordVO> list() {
        return withdrawalService.list(Integer.valueOf(request.getAttribute("User-ID").toString()));
    }

    @PostMapping("/widthdrawalList")
    @ApiOperation("提现列表接口")
    public CommonListVO<WithdrawalQueryVO> widthdrawalList(@RequestBody @Valid WithdrawalQueryDTO withdrawalQuery) {
        return withdrawalService.widthdrawalList(withdrawalQuery,Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
