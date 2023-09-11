package com.qihang.controller.realm.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author qihang
 * @since 2023-02-27
 */
@Data
public class SysDomainDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "app地址")
    @NotBlank(message = "app地址不能为空")
    private String appUrl;

    @ApiModelProperty(value = "下载地址")
    @NotBlank(message = "下载地址不能为空")
    private String downloadUrl;

}
