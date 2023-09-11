package com.qihang.controller.permutation.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 11:42
 */
@Data
public class IssueNoVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期号")
    private Integer stageNumber;
}
