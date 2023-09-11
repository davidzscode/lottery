package com.qihang.common.util.log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.area.AreaUtil;
import com.qihang.domain.log.LogDO;
import com.qihang.domain.user.SysUserDO;
import com.qihang.domain.user.UserDO;
import com.qihang.mapper.log.LogMapper;
import com.qihang.mapper.user.SysUserMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2023-02-17 16:16
 */
@Component
public class LogUtil {
    
    @Resource
    private LogMapper logMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private SysUserMapper sysUserMapper;
    
    @Resource
    private AreaUtil areaUtil;
    
    @TenantIgnore
    public void record(String desc) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Integer tenantId = Integer.valueOf(servletRequestAttributes.getRequest().getHeader("x-tenant-id"));
        Integer userId = Integer.valueOf(servletRequestAttributes.getRequest().getAttribute("User-ID").toString());
        UserDO user = userMapper.selectById(userId);
        LogDO log = new LogDO();
        log.setNickname(user.getNickname());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setPhone(user.getPhone());
        log.setUserId(userId);
        log.setTenantId(tenantId);
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
    
    @TenantIgnore
    public void record(String desc, String phone) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Integer tenantId = Integer.valueOf(servletRequestAttributes.getRequest().getHeader("x-tenant-id"));
        UserDO user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, phone).eq(UserDO::getTenantId, tenantId));
        if (user == null) {
            user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getNickname, phone).eq(UserDO::getTenantId, tenantId));
        }
        LogDO log = new LogDO();
        log.setNickname(user.getNickname());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        log.setPhone(user.getPhone());
        log.setUserId(user.getId());
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setTenantId(tenantId);
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
    
    @TenantIgnore
    public void record(String desc, String phone, Integer tenantId) {
        UserDO user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, phone).eq(UserDO::getTenantId, tenantId));
        if (user == null) {
            user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getNickname, phone).eq(UserDO::getTenantId, tenantId));
        }
        LogDO log = new LogDO();
        log.setNickname(user.getNickname());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setPhone(user.getPhone());
        log.setUserId(user.getId());
        log.setTenantId(tenantId);
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
    
    @TenantIgnore
    public void adminRecord(String desc, String phone, Integer tenantId) {
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, phone).eq(SysUserDO::getTenantId, tenantId));
        LogDO log = new LogDO();
        log.setNickname(user.getName());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setPhone(user.getUsername());
        log.setUserId(user.getId());
        log.setTenantId(tenantId);
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
    
    @TenantIgnore
    public void superAdminRecord(String desc, String phone) {
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, phone));
        LogDO log = new LogDO();
        log.setNickname(user.getName());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setPhone(user.getUsername());
        log.setUserId(user.getId());
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
    
    @TenantIgnore
    public void adminRecord(String desc) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String username = servletRequestAttributes.getRequest().getHeader("x-user");
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, username));
        LogDO log = new LogDO();
        log.setNickname(user.getName());
        log.setCreateTime(new Date());
        log.setDescriptor(desc);
        String ip = areaUtil.getIp();
        log.setIp(ip);
        if (ip != null && !ip.equals("127.0.0.1")) {
            log.setArea(areaUtil.getAreaByIp(ip));
        }
        log.setPhone(user.getUsername());
        log.setUserId(user.getId());
        log.setTenantId(user.getTenantId());
        log.setUpdateTime(new Date());
        logMapper.insert(log);
    }
}
