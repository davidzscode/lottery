package com.qihang.controller.documentary.app;


import com.qihang.common.util.log.LogUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.documentary.app.dto.CreateDocumentaryDTO;
import com.qihang.controller.documentary.app.dto.CreateDocumentaryUserDTO;
import com.qihang.controller.documentary.app.dto.ShopkeeperCreateDocumentaryUserDTO;
import com.qihang.controller.documentary.app.vo.DocumentaryByIdVO;
import com.qihang.controller.documentary.app.vo.DocumentaryByTypeVO;
import com.qihang.controller.documentary.app.vo.DocumentaryDetailsVO;
import com.qihang.controller.racingball.app.vo.RacingBallOrderVO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.mapper.user.UserMapper;
import com.qihang.service.documentary.IDocumentaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

/**
 * @author qihang
 * @since 2022-11-10
 */
@Api(tags = "app-跟单接口集合")
@RestController
@RequestMapping("/app/documentary")
public class DocumentaryController {
    
    @Resource
    private IDocumentaryService documentaryService;
    
    @Resource
    private ServletRequest request;
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private LogUtil logUtil;
    
    
    @PostMapping("/create")
    @ApiOperation("创建发单接口")
    public BaseVO create(@RequestBody @Valid CreateDocumentaryDTO createDocumentary) {
        UserDO userDO = userMapper.selectById(Integer.valueOf(request.getAttribute("User-ID").toString()));
        if (!userDO.getIsAgent().equals(IsAgentEnum.User.getKey())) {
            return new BaseVO(false, ErrorCodeEnum.E110.getKey(), ErrorCodeEnum.E110.getValue());
        }
        logUtil.record("发布跟单");
        return documentaryService.create(createDocumentary, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @GetMapping("/ranking")
    @ApiOperation("跟单排行榜接口")
    public BaseVO ranking() {
        return documentaryService.ranking();
    }
    
    @GetMapping("/details/{id}")
    @ApiOperation("根据用户id查询跟单信息接口")
    public DocumentaryDetailsVO documentaryDetails(@PathVariable("id") Integer id) {
        return documentaryService.documentaryDetails(Integer.valueOf(request.getAttribute("User-ID").toString()), id);
    }
    
    @GetMapping("/by/{type}")
    @ApiOperation("根据类型查询对应的跟单数据")
    public CommonListVO<DocumentaryByTypeVO> queryDocumentaryByType(@PathVariable("type") String type) {
        return documentaryService.queryDocumentaryByType(type, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @GetMapping("/get/{id}/{userId}")
    @ApiOperation("根据跟单id和目標用户id查询跟单信息")
    public DocumentaryByIdVO queryDocumentaryById(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
        return documentaryService.queryDocumentaryById(id, Integer.valueOf(request.getAttribute("User-ID").toString()), userId);
    }
    
    @PostMapping("/add")
    @ApiOperation("创建用户跟单接口")
    RacingBallOrderVO createDocumentaryUser(@RequestBody @Valid CreateDocumentaryUserDTO createDocumentaryUser) {
        return documentaryService.createDocumentaryUser(createDocumentaryUser, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/shopkeeperMoniAdd")
    @ApiOperation("店主模拟用户跟单接口")
    BaseVO shopkeeperMoniAdd(@RequestBody @Valid ShopkeeperCreateDocumentaryUserDTO createDocumentaryUser) throws Exception {
        return documentaryService.shopkeeperCreateDocumentaryMoniUser(createDocumentaryUser, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
}
