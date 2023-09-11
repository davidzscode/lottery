package com.qihang.service.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.domain.user.SysUserDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.mapper.user.SysUserMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version : 登录处理
 * @author: qihang
 * @date:Created in 2020/6/24 18:43
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        //获取用户信息
        if (StrUtil.isNumeric(phone)) {
            UserDO user = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, phone));
            if (null == user) {
                //仍需要细化处理
                throw new UsernameNotFoundException(ErrorCodeEnum.E0756.getValue());
            }
            user.setUsePhoneLogin(true);
            return user;
        } else {
            //后台
            UserDO appUser = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getNickname, phone));
            if (appUser != null) {
                appUser.setUsePhoneLogin(false);
                return appUser;
            } else {
                SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, phone));
                if (null == user) {
                    //仍需要细化处理
                    throw new UsernameNotFoundException(ErrorCodeEnum.E0756.getValue());
                }
                return user;
            }
        }
    }
}
