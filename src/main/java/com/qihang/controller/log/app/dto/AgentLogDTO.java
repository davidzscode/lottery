package com.qihang.controller.log.app.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qihang
 * @since 2022-10-08
 */
@Data
@Accessors(chain = true)
public class AgentLogDTO extends PageDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号")
    private String phone;

}
