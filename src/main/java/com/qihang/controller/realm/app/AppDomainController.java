package com.qihang.controller.realm.app;


import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.realm.admin.vo.SysDomainVO;
import com.qihang.service.realm.ISysDomainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/app/domain")
public class AppDomainController {

    @Resource
    private ISysDomainService sysDomainService;

    @GetMapping("/query")
    @ApiOperation("域名接口")
    public CommonListVO<SysDomainVO> queryDomain() {
        return sysDomainService.queryDomain();
    }
}
