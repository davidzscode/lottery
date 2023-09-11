package com.qihang.controller.permutation.app;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.permutation.app.dto.PlaceOrderDTO;
import com.qihang.controller.permutation.app.vo.IssueNoVO;
import com.qihang.controller.permutation.app.vo.PermutationRecordVO;
import com.qihang.service.permutation.IPermutationAwardService;
import com.qihang.service.permutation.IPermutationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author qihang
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/app/permutation")
@Api(tags = "APP - 排列")
public class AppPermutationController {

    @Resource
    private IPermutationService permutationService;

    @Resource
    IPermutationAwardService permutationAwardService;

    @Resource
    private ServletRequest request;


    @PostMapping("/place/{type}")
    @ApiOperation("排列下单接口")
    public BaseVO placeOrder(@RequestBody @Valid List<PlaceOrderDTO> placeList, @PathVariable String type) {
        return permutationService.placeOrder(placeList, Integer.valueOf(request.getAttribute("User-ID").toString()), type);
    }


    @GetMapping("/record/{type}")
    @ApiOperation("排列出奖记录接口")
    public CommonListVO<PermutationRecordVO> record(@PathVariable String type) {
        return permutationService.record(type);
    }


    @GetMapping("/issue/{type}")
    @ApiOperation("获取排列期号接口")
    public IssueNoVO getIssueNo(@PathVariable String type) {
        return permutationAwardService.getIssueNo(type);
    }
}
