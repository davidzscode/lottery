package com.qihang.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.log.admin.dto.LogQueryDTO;
import com.qihang.controller.log.admin.vo.LogQueryVO;
import com.qihang.controller.log.app.dto.AgentLogDTO;
import com.qihang.controller.log.app.vo.AgentLogVO;
import com.qihang.domain.log.LogDO;

/**
 * @author: qihang
 * @description:
 * @time: 2023-02-17 17:34
 */
public interface LogService extends IService<LogDO> {
    /**
     * 分页查询日志信息
     *
     * @param logQuery
     * @return
     */
    CommonListVO<LogQueryVO> pageList(LogQueryDTO logQuery);


    /**
     * app前台日志展示
     *
     * @param userId
     * @param agentLog
     * @return
     */
    CommonListVO<AgentLogVO> agentLogList(AgentLogDTO agentLog, Integer userId);
}
