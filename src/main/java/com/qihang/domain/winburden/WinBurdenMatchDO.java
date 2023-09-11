package com.qihang.domain.winburden;

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
 * @author qihang
 * @since 2023-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_win_burden_match")
public class WinBurdenMatchDO implements Serializable {

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
     * 分析
     */
    private String analysis;


    /**
     * 期号
     */
    private String issueNo;


    /**
     * 不让球赔率
     */
    private String notLetOdds;

    /**
     * 开赛时间
     */
    private String openTime;

    /**
     * 主队
     */
    private String homeTeam;

    /**
     * 下注状态 0 不可下注 1 可下注
     */
    private String state;

    /**
     * 客队
     */
    private String visitingTeam;

    /**
     * 比赛下注截止时间
     */
    private Date deadline;

    /**
     * 开奖结果
     */
    private String award;

    /**
     * 奖金
     */
    private String moneyAward;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
