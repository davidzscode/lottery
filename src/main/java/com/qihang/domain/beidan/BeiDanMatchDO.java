package com.qihang.domain.beidan;

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
 * 北单比赛
 *
 * @author qihang
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_beidan_match")
public class BeiDanMatchDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 颜色
     */
    private String color;

    /**
     * 赛事
     */
    @TableField(value = "`match`")
    private String match;

    /**
     * 分析链接
     */
    private String analysis;

    /**
     * 让球赔率
     */
    private String letOdds;

    /**
     * 下注状态 0 不可下注 1 可下注
     */
    private String state;

    /**
     * 比分赔率
     */
    private String scoreOdds;

    /**
     * 上下单双赔率
     */
    private String oddEvenOdds;

    /**
     * 半全场赔率
     */
    private String halfWholeOdds;

    /**
     * 进球赔率
     */
    private String goalOdds;


    /**
     * 主队
     */
    private String homeTeam;

    /**
     * 客队
     */
    private String visitingTeam;

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
     * 让球
     */
    private String letBall;

    /**
     * 球赛开始时间
     */
    private String startTime;

    /**
     * 开奖
     */
    private String award;

    /**
     * 半全场结果
     */
    private String halfFullCourt;

    /**
     * 出奖赔率
     */
    private String bonusOdds;
}
