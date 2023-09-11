package com.qihang.controller.complaints.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lihv
 * @since 2023/7/27
 */
@Data
public class ComplaintsDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Integer id;
    
    
}
