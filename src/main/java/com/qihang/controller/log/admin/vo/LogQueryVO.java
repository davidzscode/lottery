package com.qihang.controller.log.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qihang
 * @since 2022-10-08
 */
@Data
@Accessors(chain = true)
public class LogQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "地区")
    private String area;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "操作方式")
    private String descriptor;

    @ApiModelProperty(value = "操作时间")
    private Date createTime;

}
