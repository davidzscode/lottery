package com.qihang.controller.complaints.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lihv
 * @since 2023/7/27
 */
@Data
public class ComplaintsCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 投诉标题
     */
    @NotBlank(message = "投诉标题不能为空")
    @ApiModelProperty(value = "投诉标题")
    private String title;
    
    /**
     * 投诉内容
     */
    @NotBlank(message = "投诉内容不能为空")
    @ApiModelProperty(value = "投诉内容")
    private String content;
    
}
