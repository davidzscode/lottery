package com.qihang.controller.grandlotto.vo;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.permutation.app.vo.PermutationVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2023-04-05 11:36
 */
@Data
public class GrandLottoVO extends BaseVO {
    @ApiModelProperty(value = "总注数")
    private Integer notes;

    @ApiModelProperty(value = "组合")
    List<PermutationVO> permutationList;
}
