package com.qihang.controller.follow.app;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.user.app.vo.UserVO;
import com.qihang.service.follow.IFollowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

/**
 * @author qihang
 * @since 2022-11-12
 */
@RestController
@RequestMapping("/app/follow")
public class FollowController {

    @Resource
    private IFollowService followService;

    @Resource
    private ServletRequest request;

    @PutMapping("/relationship/{type}/{id}")
    @ApiOperation("关注关系接口")
    public BaseVO addRelationship(@PathVariable("type") String type, @PathVariable("id") Integer id) {
        return followService.addRelationship(Integer.valueOf(request.getAttribute("User-ID").toString()), id, type);
    }

    @GetMapping("/list/{type}")
    @ApiOperation("关注|粉丝列表接口")
    public CommonListVO<UserVO> list(@PathVariable("type") String type) {
        return followService.list(type, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
