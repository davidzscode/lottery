package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentarySagaVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "达人用户列表")
    private List<UserRankingVO> expertUserList;

    @ApiModelProperty(value = "连红用户列表")
    private List<UserRankingVO> lianHongUserList;

    @ApiModelProperty(value = "胜率用户列表")
    private List<UserRankingVO> winUserList;

    @ApiModelProperty(value = "盈利用户列表")
    private List<UserRankingVO> profitUserList;
}
