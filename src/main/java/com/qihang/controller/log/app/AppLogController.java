package com.qihang.controller.log.app;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.log.app.dto.AgentLogDTO;
import com.qihang.service.log.LogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author: qihang
 * @description:
 * @time: 2023-02-17 17:35
 */
@RestController
@RequestMapping("/app/log")
public class AppLogController {

    @Resource
    private LogService logService;

    @Resource
    private ServletRequest request;

    @PostMapping("/list")
    @ApiOperation("当前用户代理下客户操作日志列表")
    public BaseVO agentLogList(@RequestBody @Valid AgentLogDTO agentLog) {
        return logService.agentLogList(agentLog, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
