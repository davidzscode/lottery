package com.qihang.domain.basketball;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 篮球比赛
 *
 * @author qihang
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_basketball_match")
public class BasketballMatchDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 赛事
     */
    @TableField(value = "`match`")
    private String match;


    /**
     * 颜色
     */
    private String color;

    /**
     * 分析链接
     */
    private String analysis;

    /**
     * 开赛时间
     */
    private String openTime;

    /**
     * 胜负赔率
     */
    private String winNegativeOdds;

    /**
     * 下注状态 0 不可下注 1 可下注
     */
    private String state;

    /**
     * 让分
     */
    private String cedePoints;

    /**
     * 让分胜负赔率
     */
    private String cedePointsOdds;

    /**
     * 大小分赔率
     */
    private String sizeOdds;

    /**
     * 胜负差赔率
     */
    private String differenceOdds;


    /**
     * 开奖
     */
    private String award;

    /**
     * 半全场结果
     */
    private String halfFullCourt;

    /**
     * 主队
     */
    private String homeTeam;

    /**
     * 客队
     */
    private String visitingTeam;

    /**
     * 是否单关 0 否 1 是
     */
    private String isSingle;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 下注截止时间
     */
    private Date deadline;

    /**
     * 球赛开始时间
     */
    private String startTime;
}
