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
 * @time: 2022-06-28 10:16
 */
@Data
public class UserLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户", required = true)
    @NotBlank(message = "账户不能为空")
//    @Pattern(regexp = "0?(13|14|15|17|18|19)[0-9]{9}", message = "请输入正确的手机号或用户名")
    private String phone;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须大于6位且小于16")
    private String password;
}
