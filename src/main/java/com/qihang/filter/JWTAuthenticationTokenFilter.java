package com.qihang.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.qihang.common.util.jwt.JWTUtil;
import com.qihang.common.util.response.ResponseUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.constant.Constant;
import com.qihang.domain.shop.ShopDO;
import com.qihang.domain.user.SysUserDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.line.LineEnum;
import com.qihang.mapper.shop.ShopMapper;
import com.qihang.mapper.user.SysUserMapper;
import com.qihang.mapper.user.UserMapper;
import com.qihang.tenant.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: qihang
 * @description: JWT接口请求校验拦截器请求接口时会进入这里验证Token是否合法和过期
 * @time: 2022-07-13 16:22
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
    private UserMapper userMapper;
    
    private SysUserMapper sysUserMapper;
    
    private ShopMapper shopMapper;
    
    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager, UserMapper userMapper, SysUserMapper sysUserMapper, ShopMapper shopMapper) {
        super(authenticationManager);
        this.userMapper = userMapper;
        this.sysUserMapper = sysUserMapper;
        this.shopMapper = shopMapper;
    }
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String token = request.getHeader("x-access-token");
        String sys = request.getHeader("x-sys");
        String tenantId = request.getHeader("x-tenant-id");
        if (StringUtils.isNotEmpty(tenantId)) {
            ShopDO shopDO = shopMapper.selectById(tenantId);
            if (ObjectUtil.isNotNull(shopDO) && !shopDO.getLine().equals(LineEnum.UPPER.getKey())) {
                ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E085.getKey(), ErrorCodeEnum.E085.getValue()));
            }
        }
        TenantContextHolder.setIgnore(false);
        if (null != token) {
            try {
                // 解析JWT
                Map<String, Object> map = JWTUtil.decodeToken(token, Constant.SECRET);
                Integer userId = Integer.valueOf(map.get("userId").toString());
                String pwd = map.get("pwd").toString();
                UsernamePasswordAuthenticationToken authenticationToken = null;
                Integer superadmin = null;
                if (StrUtil.isBlank(sys)) {
                    //普通用户禁止访问包含admin的地址
                    String requestURI = request.getRequestURI();
                    if (requestURI.contains("admin")) {
                        log.warn("APP用户禁止访问admin接口：" + userId + requestURI);
                        ResponseUtil.ResponseMeg(response, new BaseVO(false, null, "APP用户禁止访问后台接口"));
                        return;
                    }
                    //校验密码是否修改
                    UserDO user = userMapper.selectById(userId);
                    if (!DigestUtil.md5Hex(user.getPassword()).equals(pwd)) {
                        ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0752.getKey(), ErrorCodeEnum.E0752.getValue()));
                    }
                    authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                } else {
                    TenantContextHolder.setIgnore(true);
                    SysUserDO user = sysUserMapper.selectById(userId);
                    if (user == null || user.getSuperadmin() == null || user.getSuperadmin() != 1) {
                        TenantContextHolder.setIgnore(false);
                    } else {
                        log.debug("SuperAdmin：" + user.getSuperadmin());
                    }
                    //校验密码是否修改
                    if (!DigestUtil.md5Hex(user.getPassword()).equals(pwd)) {
                        ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0752.getKey(), ErrorCodeEnum.E0752.getValue()));
                    }
                    authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    superadmin = user.getSuperadmin();
                }
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.setAttribute("User-ID", userId);
                request.setAttribute("User-SuperAdmin", superadmin);
                filterChain.doFilter(request, response);
            } catch (TokenExpiredException e) {
                ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0752.getKey(), ErrorCodeEnum.E0752.getValue()));
                return;
            } catch (SignatureVerificationException e) {
                ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0753.getKey(), ErrorCodeEnum.E0753.getValue()));
                return;
            } catch (JWTDecodeException e3) {
                ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0754.getKey(), ErrorCodeEnum.E0754.getValue()));
                return;
            }
        } else {
            ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0753.getKey(), ErrorCodeEnum.E0753.getValue()));
            return;
        }
    }
}
