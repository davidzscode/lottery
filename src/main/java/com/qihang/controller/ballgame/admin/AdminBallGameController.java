package com.qihang.controller.ballgame.admin;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.ballgame.admin.dto.AdminBallGameDTO;
import com.qihang.service.ballgame.IBallGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author: qihang
 * @description:
 * @time: 2022-10-03 21:04
 */
@RestController
@Api(tags = "admin 彩票接口集合")
@RequestMapping("/admin/ball")
public class AdminBallGameController {
    
    @Resource
    private IBallGameService ballGameService;
    
    @GetMapping("/list")
    @ApiOperation("福彩接口")
    public BaseVO list() {
        return ballGameService.adminList();
    }
    
    @PutMapping("/update/line/{id}/{type}")
    @ApiOperation("福彩上下架接口")
    public BaseVO updateLine(@PathVariable("id") Integer id, @PathVariable("type") String type) {
        return ballGameService.updateLine(id, type);
    }
    
    @PutMapping("/updateLine")
    @ApiOperation("店铺游戏上下架接口")
    public BaseVO updateLine(AdminBallGameDTO dto) {
        return ballGameService.updateLine(dto);
    }
}
