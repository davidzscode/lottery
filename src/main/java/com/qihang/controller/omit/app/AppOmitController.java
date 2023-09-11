package com.qihang.controller.omit.app;


import com.qihang.controller.omit.app.vo.OmitVO;
import com.qihang.service.omit.IOmitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2023-04-07
 */
@RestController
@RequestMapping("/app/omit")
public class AppOmitController {

    @Resource
    private IOmitService omitService;

    @GetMapping("/get/{type}")
    @ApiOperation("根据类型查询遗漏")
    public OmitVO getOmitByType(@PathVariable("type") String type) {
        return omitService.getOmitByType(type);
    }
}
