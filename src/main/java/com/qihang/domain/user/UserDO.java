package com.qihang.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qihang.enumeration.user.UserStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author qihang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class UserDO implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    
    /**
     * uid
     */
    private Integer uid;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 0.男 1.女 2.未知
     */
    private String sex;
    
    /**
     * 彩金
     */
    private BigDecimal gold;
    
    /**
     * 奖金
     */
    private BigDecimal price;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 身份证号
     */
    private String idCard;
    
    /**
     * 生日
     */
    private Date birthday;
    
    /**
     * 0 正常 1.禁用
     */
    private String status;
    
    /**
     * 用户类型 1=店主  2=销售 0=用户
     */
    
    private String isAgent;
    
    /**
     * ip
     */
    private String ip;
    
    
    /**
     * 地区
     */
    private String area;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    
    /**
     * 0 未实名 1 实名
     */
    private String isReal;
    
    /**
     * 二维码地址
     */
    @TableField("qr_code")
    private String qrCode;
    
    /**
     * 关注
     */
    private Integer follow;
    
    /**
     * 粉丝
     */
    private Integer fans;
    
    /**
     * 上级
     */
    private Integer pid;
    
    /**
     * 店长id
     */
    private Integer ppid;
    
    /**
     * 微信号
     */
    private String wechat;
    
    /**
     * 银行账号
     */
    private String bankNumber;
    
    /**
     * 支付宝账户
     */
    private String zfbNumber;
    
    /**
     * 银行名称
     */
    private String bankName;
    
    /**
     * 租户id
     */
    private Integer tenantId;
    
    /**
     * 是否是模拟用户，1是，0不是
     */
    private Integer moni;
    
    /**
     * 跟单中奖分佣比率(/100)
     */
    private Integer commissionRate;
    
    /**
     * 销售分佣比率
     */
    private BigDecimal saleRate;
    
    /**
     * 是否用手机号登录
     */
    @TableField(exist = false)
    private Boolean usePhoneLogin;

    /**
     * 是否星级用户
     * */
    private Integer isStar;

    /**
     * 是否充值用户
     * */
    private Integer rechargeStatus;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    @Override
    public String getUsername() {
        if (usePhoneLogin == null || usePhoneLogin) {
            return phone;
        } else {
            return nickname;
        }
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
