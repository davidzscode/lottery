package com.qihang.service.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.jwt.JWTUtil;
import com.qihang.common.util.log.LogUtil;
import com.qihang.common.util.security.BcryptPasswordUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.shop.admin.vo.AdminShopVO;
import com.qihang.controller.sys.dto.*;
import com.qihang.controller.sys.vo.AdminUserListVO;
import com.qihang.controller.sys.vo.AdminUserQueryVO;
import com.qihang.controller.sys.vo.AdminUserTokenVO;
import com.qihang.domain.shop.ShopDO;
import com.qihang.domain.user.SysUserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.mapper.shop.ShopMapper;
import com.qihang.mapper.user.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qihang
 * @since 2022-10-21
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {
    
    @Resource
    private SysUserMapper sysUserMapper;
    /**
     * 延迟加载，因为存在相互依赖的问题
     */
    @Lazy
    @Resource
    private AuthenticationManager authenticationManager;
    
    @Resource
    private ShopMapper shopMapper;
    
    @Resource
    private LogUtil logUtil;
    
    @Override
    public BaseVO login(AdminUserLoginDTO adminUserLoginDTO) {
        Authentication authentication;
        try {
            // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
            // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminUserLoginDTO.getUsername(), adminUserLoginDTO.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return new BaseVO(false, ErrorCodeEnum.E0751.getKey(), ErrorCodeEnum.E0751.getValue());
        } catch (DisabledException disabledException) {
            return new BaseVO(false, ErrorCodeEnum.E0757.getKey(), ErrorCodeEnum.E0757.getValue());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new BaseVO(false, ErrorCodeEnum.E0756.getKey(), ErrorCodeEnum.E0756.getValue());
        } catch (AuthenticationException authenticationException) {
            return new BaseVO(false, ErrorCodeEnum.E0759.getKey(), ErrorCodeEnum.E0759.getValue());
        }
        SysUserDO user = (SysUserDO) authentication.getPrincipal();
        //生成令牌
        String token = JWTUtil.createToken(user.getId(), DigestUtil.md5Hex(user.getPassword()));
        //获取店铺信息
        ShopDO shop = shopMapper.selectById(user.getTenantId());
        AdminShopVO adminShop = new AdminShopVO();
        BeanUtils.copyProperties(shop, adminShop);
        //写入到vo
        AdminUserTokenVO userTokenVO = new AdminUserTokenVO(token, user.getId(), user.getName(), user.getUsername(), user.getAvatar(), adminShop);
        logUtil.adminRecord("后台登录", adminUserLoginDTO.getUsername(), user.getTenantId());
        return userTokenVO;
    }
    
    /**
     * @param adminUserLoginDTO
     */
    @TenantIgnore
    @Override
    public BaseVO loginSuperAdmin(AdminUserLoginDTO adminUserLoginDTO) {
        Authentication authentication;
        try {
            // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
            // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminUserLoginDTO.getUsername(), adminUserLoginDTO.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return new BaseVO(false, ErrorCodeEnum.E0751.getKey(), ErrorCodeEnum.E0751.getValue());
        } catch (DisabledException disabledException) {
            return new BaseVO(false, ErrorCodeEnum.E0757.getKey(), ErrorCodeEnum.E0757.getValue());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new BaseVO(false, ErrorCodeEnum.E0756.getKey(), ErrorCodeEnum.E0756.getValue());
        } catch (AuthenticationException authenticationException) {
            return new BaseVO(false, ErrorCodeEnum.E0759.getKey(), ErrorCodeEnum.E0759.getValue());
        }
        SysUserDO user = (SysUserDO) authentication.getPrincipal();
        //生成令牌
        String token = JWTUtil.createToken(user.getId(), DigestUtil.md5Hex(user.getPassword()));
        //写入到vo
        AdminUserTokenVO userTokenVO = new AdminUserTokenVO(token, user.getId(), user.getName(), user.getUsername(), user.getAvatar(), null);
        logUtil.superAdminRecord("超管后台登录", adminUserLoginDTO.getUsername());
        return userTokenVO;
    }
    
    @Override
    public AdminUserTokenVO getTokenByUserName(String username) {
        //这个接口主要是处理 当后台切换到其它竞彩店铺的时候需要前端需要替换token
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, username));
        //生成令牌
        String token = JWTUtil.createToken(user.getId(), DigestUtil.md5Hex(user.getPassword()));
        ShopDO shop = shopMapper.selectById(user.getTenantId());
        AdminShopVO adminShop = new AdminShopVO();
        BeanUtils.copyProperties(shop, adminShop);
        AdminUserTokenVO userTokenVO = new AdminUserTokenVO(token, user.getId(), user.getName(), user.getUsername(), user.getAvatar(), adminShop);
        return userTokenVO;
    }
    
    @Override
    @TenantIgnore
    public CommonListVO<AdminUserListVO> queryUserByTenantId(Integer tenantId) {
        CommonListVO<AdminUserListVO> commonList = new CommonListVO<>();
        List<SysUserDO> userList = sysUserMapper.selectList(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getTenantId, tenantId));
        List<AdminUserListVO> list = BeanUtil.copyToList(userList, AdminUserListVO.class);
        commonList.setVoList(list);
        return commonList;
    }
    
    @Override
    public BaseVO verifyPayPwd(VerifyPayPwdDTO verifyPayPwdDTO) {
        SysUserDO sysUserDO = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, verifyPayPwdDTO.getUsername()));
        if (!sysUserDO.getPayPwd().equals(verifyPayPwdDTO.getPayPwd())) {
            return new BaseVO(false, ErrorCodeEnum.E086.getKey(), ErrorCodeEnum.E086.getValue());
        }
        return new BaseVO();
    }
    
    @Override
    public BaseVO update(UpdateUserDTO updateUserDTO) {
        SysUserDO sysUserDO = new SysUserDO();
        SysUserDO sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, updateUserDTO.getUsername()));
        if (StrUtil.isNotBlank(updateUserDTO.getOldPassword()) && StrUtil.isNotBlank(updateUserDTO.getPassword())) {
            log.info(String.valueOf(new BCryptPasswordEncoder().matches(updateUserDTO.getOldPassword(), sysUser.getPassword())));
            if (!new BCryptPasswordEncoder().matches(updateUserDTO.getOldPassword(), sysUser.getPassword())) {
                return new BaseVO(false, ErrorCodeEnum.E087.getKey(), ErrorCodeEnum.E087.getValue());
            }
            sysUserDO.setPassword(BcryptPasswordUtil.createBCryptPassword(updateUserDTO.getPassword()));
        }
        if (ObjectUtil.isNotNull(updateUserDTO.getOldPayPwd()) && ObjectUtil.isNotNull(updateUserDTO.getPayPwd())) {
            if (!sysUser.getPayPwd().equals(updateUserDTO.getOldPayPwd())) {
                return new BaseVO(false, ErrorCodeEnum.E088.getKey(), ErrorCodeEnum.E088.getValue());
            }
            sysUserDO.setPayPwd(updateUserDTO.getPayPwd());
        }
        sysUserMapper.update(sysUserDO, new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, updateUserDTO.getUsername()));
        return new BaseVO();
    }
    
    @Override
    public CommonListVO<AdminUserQueryVO> pageList(AdminUserQueryDTO adminUserQuery) {
        CommonListVO<AdminUserQueryVO> commonList = new CommonListVO<>();
        //分页
        Page<SysUserDO> page = new Page<>(adminUserQuery.getPageNo(), adminUserQuery.getPageSize());
        
        LambdaQueryWrapper<SysUserDO> qw = new QueryWrapper<SysUserDO>().lambda();
        qw.like(StrUtil.isNotBlank(adminUserQuery.getName()), SysUserDO::getName, adminUserQuery.getName());
        qw.like(StrUtil.isNotBlank(adminUserQuery.getUsername()), SysUserDO::getUsername, adminUserQuery.getUsername());
        qw.orderByDesc(SysUserDO::getCreateTime);
        Page<SysUserDO> userPage = sysUserMapper.selectPage(page, qw);
        commonList.setVoList(BeanUtil.copyToList(userPage.getRecords(), AdminUserQueryVO.class));
        commonList.setTotal(userPage.getTotal());
        return commonList;
    }
    
    @Override
    public BaseVO delete(Integer id) {
        sysUserMapper.deleteById(id);
        return new BaseVO();
    }
    
    @Override
    public BaseVO add(AdminUserAddDTO adminUserAdd, Integer userId) {
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, adminUserAdd.getUsername()));
        if (ObjectUtil.isNotNull(user)) {
            return new BaseVO(false, ErrorCodeEnum.E090.getKey(), ErrorCodeEnum.E090.getValue());
        }
        SysUserDO sysUserDO = sysUserMapper.selectById(userId);
        SysUserDO sysUser = new SysUserDO();
        sysUser.setPassword(BcryptPasswordUtil.createBCryptPassword(adminUserAdd.getPassword()));
        sysUser.setUsername(adminUserAdd.getUsername());
        sysUser.setName(adminUserAdd.getName());
        sysUser.setPayPwd(adminUserAdd.getPayPwd());
        sysUser.setCreateUser(sysUserDO.getUsername());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setLastLoginDate(new Date());
        sysUser.setUpdateUser(sysUserDO.getUsername());
        sysUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
        sysUserMapper.insert(sysUser);
        return new BaseVO();
    }
    
}
