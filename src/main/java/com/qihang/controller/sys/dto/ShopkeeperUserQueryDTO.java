package com.qihang.controller.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 12:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopkeeperUserQueryDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
}
