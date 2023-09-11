package com.qihang.controller.grandlotto.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2023-04-05 11:40
 */
@Data
public class GrandLottoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "红球")
    List<GrandLottoObjDTO> redList;

    @ApiModelProperty(value = "篮球")
    List<GrandLottoObjDTO> blueList;
}
