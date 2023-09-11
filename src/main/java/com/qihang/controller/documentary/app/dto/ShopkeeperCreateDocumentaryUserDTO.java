package com.qihang.controller.documentary.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
@Accessors(chain = true)
public class ShopkeeperCreateDocumentaryUserDTO extends CreateDocumentaryUserDTO {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "模拟用户id")
    @NotNull(message = "模拟用户id不能为空")
    private List<Integer> userIdList;
}
