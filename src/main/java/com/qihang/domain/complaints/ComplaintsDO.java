package com.qihang.domain.complaints;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 投诉表
 * @author lihv
 * @since 2023/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_complaints")
public class ComplaintsDO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 投诉人
     */
    private Integer userId;
    
    /**
     * 店主id
     */
    private Integer tenantId;
    
    /**
     * 投诉标题
     */
    private String title;
    
    /**
     * 投诉内容
     */
    private String content;
    
    /**
     * 回复
     */
    private String answer;
    
    /**
     * 创建时间
     */
    private Date addTime;
    /**
     * 创建时间
     */
    private Date ansTime;
    
    /**
     * 是否回复 0未回复，1已回复
     */
    private Integer ifAnswer;
    
    
}
