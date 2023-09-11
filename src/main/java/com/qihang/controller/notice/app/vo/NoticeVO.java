package com.qihang.controller.notice.app.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qihang
 * @since 2022-10-08
 */
@Data
@Accessors(chain = true)
public class NoticeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公告")
    private String msg;

    @ApiModelProperty(value = "公告开关状态")
    private Integer status;

}
