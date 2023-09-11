package com.qihang.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.sys.dto.*;
import com.qihang.controller.sys.vo.AdminUserListVO;
import com.qihang.controller.sys.vo.AdminUserQueryVO;
import com.qihang.controller.sys.vo.AdminUserTokenVO;
import com.qihang.domain.user.SysUserDO;

/**
 * @author qihang
 * @since 2022-10-21
 */
public interface ISysUserService extends IService<SysUserDO> {
    /**
     * 登录
     * @param adminUserLoginDTO
     */
    BaseVO login(AdminUserLoginDTO adminUserLoginDTO);
    
    BaseVO loginSuperAdmin(AdminUserLoginDTO userLoginDTO);
    
    
    /**
     * 根据用户名获取token
     * @param username
     */
    AdminUserTokenVO getTokenByUserName(String username);
    
    /**
     * 根据租户id查询对应的系统账户列表
     * @param tenantId
     */
    CommonListVO<AdminUserListVO> queryUserByTenantId(Integer tenantId);
    
    
    /**
     * 支付密码校验
     * @param verifyPayPwdDTO
     */
    BaseVO verifyPayPwd(VerifyPayPwdDTO verifyPayPwdDTO);
    
    /**
     * 修改
     * @param updateUserDTO
     */
    BaseVO update(UpdateUserDTO updateUserDTO);
    
    
    /**
     * 系统用户列表
     * @param adminUserQuery
     */
    CommonListVO<AdminUserQueryVO> pageList(AdminUserQueryDTO adminUserQuery);
    
    
    /**
     * 删除用户
     * @param id
     */
    BaseVO delete(Integer id);
    
    
    /**
     * 添加用户
     * @param adminUserAdd
     * @param userId
     */
    BaseVO add(AdminUserAddDTO adminUserAdd, Integer userId);
    
}