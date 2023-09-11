package com.qihang.controller.complaints.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lihv
 * @since 2023/7/27
 */
@Data
public class ComplaintsContentVO extends BaseVO {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    @ApiModelProperty(value = "投诉人id")
    private Integer userId;
    /**
     * 投诉人
     */
    @ApiModelProperty(value = "投诉人")
    private String userName;
    
    
    /**
     * 投诉标题
     */
    @ApiModelProperty(value = "投诉标题")
    private String title;
    
    /**
     * 投诉内容
     */
    @ApiModelProperty(value = "投诉内容")
    private String content;
    
    /**
     * 回复
     */
    @ApiModelProperty(value = "回复")
    private String answer;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date addTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "回复时间")
    private Date ansTime;
    
    /**
     * 是否回复 0未回复，1已回复
     */
    @ApiModelProperty(value = "是否回复 0未回复，1已回复")
    private Integer ifAnswer;
}
