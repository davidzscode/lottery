package com.qihang.tenant;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.qihang.annotation.TenantIgnore;
import com.qihang.domain.user.SysUserDO;
import com.qihang.service.user.ISysUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;


/**
 * @author: qihang
 * @description:
 * @time: 2022-11-12 21:24
 */
@Slf4j
@Data
@Component
public class MyTenantLineHandler implements TenantLineHandler {
    
    @Resource
    private MyTenantConfigProperties properties;
    
    /**
     * 获取租户 ID 值表达式，只支持单个 ID 值
     * @return 租户 ID 值表达式
     */
    
    @Override
    @TenantIgnore
    public Expression getTenantId() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String tenantId = servletRequestAttributes.getRequest().getHeader("x-tenant-id");
        log.info("当前租户为 >> {}", tenantId);
        if (StrUtil.isBlank(tenantId)) {
            //如果为空我这里就默认认定是后台系统 获取对应的手机号
            String username = servletRequestAttributes.getRequest().getHeader("x-user");
            ISysUserService sysUserService = SpringUtil.getBean(ISysUserService.class);
            SysUserDO user = sysUserService.getOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, username));
            return new StringValue(user.getTenantId().toString());
        }
        return new StringValue(tenantId);
    }
    
    
    /**
     * 获取租户字段名
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return properties.getColumn();
    }
    
    /**
     * 根据表名判断是否忽略拼接多租户条件
     * <p>
     * 默认都要进行解析并拼接多租户条件
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
     */
    
    @Override
    public boolean ignoreTable(String tableName) {
        return TenantContextHolder.isIgnore() || properties.getTables().contains(tableName);
    }
}
