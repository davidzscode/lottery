package com.qihang.controller.dict.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihv
 * @since 2023/8/7
 */
@Data
public class SysDictVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    /**
     * 字典类型
     */
    private String code;
    
    @ApiModelProperty(value = "描述")
    private String dictDesc;
    
    @ApiModelProperty(value = "数据值")
    private String value;
    
    /**
     * 租户id
     */
    private Integer tenantId;
    
    /**
     * 通道id
     */
    private Integer channelId;
}
