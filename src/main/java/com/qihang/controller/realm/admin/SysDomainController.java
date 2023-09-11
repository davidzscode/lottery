package com.qihang.controller.realm.admin;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.realm.admin.dto.SysDomainDTO;
import com.qihang.controller.realm.admin.vo.SysDomainVO;
import com.qihang.service.realm.ISysDomainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/admin/domain")
public class SysDomainController {

    @Resource
    private ISysDomainService sysDomainService;

    @GetMapping("/query")
    @ApiOperation("域名接口")
    public CommonListVO<SysDomainVO> queryDomain() {
        return sysDomainService.queryDomain();
    }


    @PutMapping("/update/{id}")
    @ApiOperation("修改域名")
    public BaseVO update(@RequestBody @Valid SysDomainDTO sysDomain, @PathVariable("id") Integer id) {
        return sysDomainService.update(sysDomain, id);
    }
}
