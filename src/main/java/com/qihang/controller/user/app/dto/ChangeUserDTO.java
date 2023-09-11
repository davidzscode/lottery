package com.qihang.controller.user.app.dto;

import com.qihang.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-08-01 17:50
 */
@Data
public class ChangeUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", required = true)
    @Pattern(regexp = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+", message = "昵称不能有特殊符合")
    private String nickname;

    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像", required = true)
    private String avatar;
    
    @NotBlank(message = "性别不能为空")
    @Range(min = 0, max = 3, message = "不能传入无法识别字符")
    @ApiModelProperty(value = "性别", required = true)
    private String sex;
    
    @DateTimeFormat(pattern = Constant.FORMAT_YEAR_MONTH_DAY)
    @ApiModelProperty(value = "出生日期", required = true)
    private Date birthday;
    
    @ApiModelProperty(value = "微信号")
    private String wechat;
}
