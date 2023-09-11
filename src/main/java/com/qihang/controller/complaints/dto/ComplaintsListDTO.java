package com.qihang.controller.complaints.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lihv
 * @since 2023/7/27
 */
@Data
public class ComplaintsListDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "是否回复 0未回复，1已回复")
//    private String ifAnswer;
    
    @ApiModelProperty(value = "起始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
