package com.qihang.domain.racingball;

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
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_racing_ball")
public class RacingBallDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 目标id
     */
    private Integer targetId;


    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 注数
     */
    private Integer notes;

    /**
     * 类型 2 二串一 3 串一 4 四 串一 等等
     */
    private String type;

    /**
     * 倍数
     */
    private Integer times;

    /**
     * 投注内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
