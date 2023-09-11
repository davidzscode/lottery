package com.qihang.controller.ballgame.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lihv
 * @since 2023/8/7
 */
@Data
public class BallGameWithTenantVO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    @ApiModelProperty(value = "名字")
    private String name;
    
    @ApiModelProperty(value = "url")
    private String url;
    
    @ApiModelProperty(value = "上下线状态 0 上线 1下线")
    private String line;
    
    private Integer tenantId;
    
}
