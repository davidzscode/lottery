package com.qihang.controller.complaints;


import com.alipay.api.AlipayApiException;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.complaints.dto.ComplaintsAnswerDTO;
import com.qihang.controller.complaints.dto.ComplaintsCreateDTO;
import com.qihang.controller.complaints.dto.ComplaintsDetailDTO;
import com.qihang.controller.complaints.dto.ComplaintsListDTO;
import com.qihang.controller.complaints.vo.ComplaintsContentVO;
import com.qihang.controller.complaints.vo.ComplaintsListVO;
import com.qihang.service.complaints.IComplaintsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author lihv
 * @since 2023/7/27
 */
@RestController
@RequestMapping("/complaints")
@Api(tags = "投诉接口")
public class ComplaintsController {
    
    @Resource
    private IComplaintsService complaintsService;
    @Resource
    private ServletRequest request;
    
    @PostMapping("/list")
    @ApiOperation("投诉列表查询接口")
    public CommonListVO<ComplaintsListVO> getPayOrderPage(@RequestBody @Valid ComplaintsListDTO complaintsListDTO) {
        return complaintsService.getPageList(complaintsListDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/detail")
    @ApiOperation("投诉明细查询接口")
    public ComplaintsContentVO detail(@RequestBody @Valid ComplaintsDetailDTO complaintsDetailDTO) {
        return complaintsService.detail(complaintsDetailDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/create")
    @ApiOperation("新增投诉接口")
    public BaseVO create(@RequestBody @Valid ComplaintsCreateDTO complaintsCreateDTO) throws AlipayApiException {
        return complaintsService.create(complaintsCreateDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/answer")
    @ApiOperation("回复投诉接口")
    public BaseVO answer(@RequestBody @Valid ComplaintsAnswerDTO complaintsAnswerDTO) {
        return complaintsService.answer(complaintsAnswerDTO, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
