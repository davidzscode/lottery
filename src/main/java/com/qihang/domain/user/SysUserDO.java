package com.qihang.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qihang.enumeration.user.UserStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author qihang
 * @since 2022-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUserDO implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 是否是超级管理员 1是，0否
     */
    private Integer superadmin;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 登录密码
     */
    private String password;
    
    /**
     * 支付密码
     */
    private Integer payPwd;
    
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 状态0：正常，1：锁定
     */
    private String status;
    
    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;
    
    
    /**
     * 租户id
     */
    private Integer tenantId;
    
    /**
     * 邮件
     */
    private String email;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 创建人员
     */
    private String createUser;
    
    /**
     * 修改时间
     */
    private Date updateTime;
    
    /**
     * 修改人员
     */
    private String updateUser;
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return UserStatusEnum.NORMAL.getStatus().equals(status);
    }
}
