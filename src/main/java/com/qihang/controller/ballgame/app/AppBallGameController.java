package com.qihang.controller.ballgame.app;

import com.alibaba.fastjson.JSONObject;
import com.qihang.common.vo.BaseVO;
import com.qihang.service.ballgame.IBallGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author: qihang
 * @description:
 * @time: 2022-10-03 21:04
 */
@RestController
@Api(tags = "app 彩票接口集合")
@RequestMapping("/app/ball")
public class AppBallGameController {

    @Resource
    private IBallGameService ballGameService;

    @GetMapping("/list")
    @ApiOperation("福彩列表")
    public BaseVO list() {
        return ballGameService.list();
    }


    @GetMapping("/version")
    @ApiOperation("版本信息")
    public JSONObject getVersion(){
        return ballGameService.getVersion();
    }

}
