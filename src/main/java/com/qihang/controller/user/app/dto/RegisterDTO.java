package com.qihang.controller.user.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-16 19:46
 */
@Data
public class RegisterDTO implements Serializable {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "0?(13|14|15|17|18|19|16)[0-9]{9}", message = "请输入正确的手机号")
    private String phone;

    @ApiModelProperty(value = "验证码", required = true)
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须大于6位且小于16")
    private String password;


    private Integer uid;

    @ApiModelProperty(value = "是否有上级")
    private String whether;
}
