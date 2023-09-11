package com.qihang.controller.winburden.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class WinBurdenMatchDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "编号")
    private String number;

    @ApiModelProperty(value = "赛事")
    private String match;

    //{active: true, index: 1, id: 5, describe: "平", odds: "3.91"}
    @ApiModelProperty(value = "胜平负")
    private List<Map<String, Object>> notLetOddsList;

    @ApiModelProperty(value = "主队")
    private String homeTeam;

    @ApiModelProperty(value = "客队")
    private String visitingTeam;

    @ApiModelProperty(value = "是否是胆")
    private Boolean isGallbladder;
}
