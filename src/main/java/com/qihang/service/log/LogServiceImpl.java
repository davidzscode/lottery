package com.qihang.service.log;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.log.admin.dto.LogQueryDTO;
import com.qihang.controller.log.admin.vo.LogQueryVO;
import com.qihang.controller.log.app.dto.AgentLogDTO;
import com.qihang.controller.log.app.vo.AgentLogVO;
import com.qihang.domain.log.LogDO;
import com.qihang.domain.user.UserDO;
import com.qihang.mapper.log.LogMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: qihang
 * @description:
 * @time: 2023-02-17 17:34
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogDO> implements LogService {

    @Resource
    private LogMapper logMapper;


    @Resource
    private UserMapper userMapper;

    @Override
    public CommonListVO<LogQueryVO> pageList(LogQueryDTO logQuery) {
        CommonListVO<LogQueryVO> commonList = new CommonListVO<>();
        //分页
        Page<LogDO> page = new Page<>(logQuery.getPageNo(), logQuery.getPageSize());

        LambdaQueryWrapper<LogDO> qw = new QueryWrapper<LogDO>().lambda();
        qw.like(StrUtil.isNotBlank(logQuery.getNickname()), LogDO::getNickname, logQuery.getNickname());
        qw.like(StrUtil.isNotBlank(logQuery.getPhone()), LogDO::getPhone, logQuery.getPhone());
        qw.orderByDesc(LogDO::getCreateTime);
        Page<LogDO> logoPage = logMapper.selectPage(page, qw);
        commonList.setVoList(BeanUtil.copyToList(logoPage.getRecords(), LogQueryVO.class));
        commonList.setTotal(logoPage.getTotal());
        return commonList;
    }

    @Override
    public CommonListVO<AgentLogVO> agentLogList(AgentLogDTO agentLog, Integer userId) {
        //分页
        Page<LogDO> page = new Page<>(agentLog.getPageNo(), agentLog.getPageSize());
        CommonListVO<AgentLogVO> commonList = new CommonListVO<>();
        List<Integer> ids = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPid, userId)).stream().map(UserDO::getId).collect(Collectors.toList());
        if(ids.size()<=0){
            return commonList;
        }
        Page<LogDO> logPage = logMapper.selectPage(page, new QueryWrapper<LogDO>().lambda()
                .in(StrUtil.isBlank(agentLog.getPhone()),LogDO::getUserId, ids)
                .eq(StrUtil.isNotBlank(agentLog.getPhone()), LogDO::getPhone, agentLog.getPhone())
                .orderByDesc(LogDO::getCreateTime));
        List<AgentLogVO> logVOList = new ArrayList<>();
        for (LogDO record : logPage.getRecords()) {
            AgentLogVO logVO = new AgentLogVO();
            BeanUtil.copyProperties(record, logVO);
            logVO.setAvatar(userMapper.selectById(record.getUserId()).getAvatar());
            logVOList.add(logVO);
        }
        commonList.setVoList(logVOList);
        commonList.setTotal(logPage.getTotal());
        return commonList;
    }
}
