package com.qihang.controller.upload.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-17 18:35
 */
@Data
public class FileVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件路径")
    private String url;
}