package com.qihang.controller.log.admin;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.log.admin.dto.LogQueryDTO;
import com.qihang.service.log.LogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: qihang
 * @description:
 * @time: 2023-02-17 17:35
 */
@RestController
@RequestMapping("/admin/log")
public class AdminLogController {

    @Resource
    private LogService logService;

    @PostMapping("/list")
    @ApiOperation("日志列表")
    public BaseVO pageList(@RequestBody @Valid LogQueryDTO logQuery) {
        return logService.pageList(logQuery);
    }
}
