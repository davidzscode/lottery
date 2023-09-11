package com.qihang.domain.permutation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qihang
 * @since 2022-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permutation")
public class PermutationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 期号
     */
    private Integer stageNumber;

    /**
     * 百万
     */
    private String hundredMyriad;

    /**
     * 十万
     */
    private String tenMyriad;

    /**
     * 万
     */
    private String myriad;


    /**
     * 千
     */
    private String kilo;

    /**
     * 百
     */
    private String hundred;

    /**
     * 十
     */
    private String ten;

    /**
     * 个
     */
    private String individual;

    /**
     * 投注方式 0 直选 1 组三 2 组九
     */
    private String mode;

    /**
     * 赛果
     */
    private String reward;

    /**
     * 注数
     */
    private Integer notes;

    /**
     * 倍数
     */
    private Integer times;


    /**
     * 3 排列3    4 排列5
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
