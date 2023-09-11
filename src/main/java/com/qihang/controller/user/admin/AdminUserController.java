package com.qihang.controller.user.admin;


import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.sys.dto.ShopkeeperUserQueryDTO;
import com.qihang.controller.sys.vo.ShopkeeperUserQueryVO;
import com.qihang.controller.user.admin.dto.*;
import com.qihang.controller.user.admin.vo.GetUserDetailVO;
import com.qihang.controller.user.app.dto.AgentSalespersonDTO;
import com.qihang.controller.user.app.dto.UserAgentDTO;
import com.qihang.controller.user.app.vo.AgentBettingVO;
import com.qihang.controller.user.app.vo.AgentSumVO;
import com.qihang.controller.user.app.vo.AgentVO;
import com.qihang.controller.user.app.vo.GetAllSaleVO;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author: qihang
 * @description:
 * @time: 2022-06-28 10:12
 */
@RestController
@Api(tags = "后台 - 普通用户管理")
@RequestMapping("/admin/user")
public class AdminUserController {
    @Resource
    private IUserService userService;
    @Resource
    private HttpServletRequest request;
    
    @PostMapping("/list")
    @ApiOperation("用户列表")
    public BaseVO list(@RequestBody @Valid UserQueryDTO userQueryDTO) {
        return userService.pageList(userQueryDTO);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户接口")
    public BaseVO delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }
    
    @PutMapping("/update/{id}")
    @ApiOperation("修改接口")
    public BaseVO updateStatus(@RequestBody @Valid UserUpdateDTO userUpdate, @PathVariable("id") Integer id) {
        return userService.updateStatus(userUpdate, id);
    }
    
    @PutMapping("/recharge/{id}")
    @ApiOperation("充值接口")
    public BaseVO recharge(@RequestBody @Valid UserRechargeDTO userRecharge, @PathVariable("id") Integer id) {
        return userService.recharge(userRecharge, id);
    }
    
    @PutMapping("/change/pwd")
    @ApiOperation("修改密码")
    public BaseVO adminChangePwd(@RequestBody @Valid AdminChangePwdDTO changePwd) {
        return userService.adminChangePwd(changePwd);
    }
    
    @PostMapping("/statistics")
    @ApiOperation("用户统计")
    public BaseVO userStatistics(@RequestBody @Valid UserStatisticsDTO userStatistics) {
        return userService.userStatistics(userStatistics);
    }
    
    @PostMapping("/add")
    @ApiOperation("添加用户")
    public BaseVO addUser(@RequestBody @Valid UserAddDTO userAdd) {
        return userService.addUser(userAdd, Integer.valueOf(request.getAttribute("User-ID").toString()));
    }
    
    @PostMapping("/shopkeeperList")
    @ApiOperation("获取所有店主")
    public CommonListVO<ShopkeeperUserQueryVO> shopkeeperList(@RequestBody @Valid ShopkeeperUserQueryDTO queryDTO) {
        return userService.shopkeeperList(queryDTO);
    }
    
    @PostMapping("/getAllSale")
    @ApiOperation("获取所有销售")
    public CommonListVO<GetAllSaleVO> getAllSale(@RequestBody @Valid GetAllSaleDTO userQueryDTO) {
        return userService.getAllUser(userQueryDTO, IsAgentEnum.Salesperson.getKey());
    }
    
    @PostMapping("/getAllUser")
    @ApiOperation("获取所有用户")
    public CommonListVO<GetAllSaleVO> getAllUser(@RequestBody @Valid GetAllSaleDTO userQueryDTO) {
        return userService.getAllUser(userQueryDTO, IsAgentEnum.User.getKey());
    }
    
    @PostMapping("/getUserDetail")
    @ApiOperation("获取用户详情")
    public GetUserDetailVO getUserDetail(@RequestBody @Valid GetUserDetailDTO userQueryDTO) {
        return userService.getUserDetail(userQueryDTO);
    }
    
    @PostMapping("/setUserAsSalesperson")
    @ApiOperation("设置用户为销售")
    public BaseVO setSalesperson(@RequestBody @Valid SetSalespersonDTO dto) {
        return userService.setUserAsSalesperson(dto);
    }
    
    @PostMapping("/setUserAsMoni")
    @ApiOperation("设置用户为模拟")
    public BaseVO setUserAsMoni(@RequestBody @Valid SetSalespersonDTO dto) {
        return userService.setUserAsMoni(dto);
    }
    
    @PostMapping("/setUserCommissionRate")
    @ApiOperation("设置用户发单的佣金比例")
    public BaseVO setUserCommissionRate(@RequestBody @Valid SetUserCommissionRateDTO dto) {
        return userService.setUserCommissionRate(dto);
    }
    
    @PostMapping("/setSalespersonSaleRate")
    @ApiOperation("设置销售的销售佣金比例")
    public BaseVO setSalespersonSaleRate(@RequestBody @Valid SetSalespersonSaleRateDTO dto) {
        return userService.setSalespersonSaleRate(dto);
    }
    
    @PostMapping("/agent")
    @ApiOperation("获取下级代理接口")
    public CommonListVO<AgentVO> agent(@RequestBody @Valid UserAgentDTO userAgent) {
        Integer salespersonId = userAgent.getSalespersonId();
        userAgent.setSalespersonId(null);
        return userService.agent(userAgent, salespersonId);
    }
    
    @PostMapping("/agentSalesperson")
    @ApiOperation("获取销售接口")
    public AgentSumVO agentSalesperson(@RequestBody @Valid AgentSalespersonDTO userAgent) {
        return userService.agentSalesperson(userAgent, userAgent.getShoperId());
    }
    
    @PostMapping("/agentBettingList")
    @ApiOperation("获取下级客户投注列表")
    public CommonListVO<AgentBettingVO> agentBettingList(@RequestBody @Valid UserAgentDTO userAgent) {
        Integer salespersonId = userAgent.getSalespersonId();
        userAgent.setSalespersonId(null);
        return userService.agentBettingList(userAgent, salespersonId);
    }


    @PutMapping("/updateUserStarStatus/{id}")
    @ApiOperation("修改是否为星级用户")
    public BaseVO updateUserStarStatus(@RequestBody @Valid updateUserStarStatusDTO userUpdate, @PathVariable("id") Integer id) {
        return userService.updateUserStarStatus(userUpdate, id);
    }

    @PutMapping("/updateRechargeStatus/{id}")
    @ApiOperation("修改是否充过值")
    public BaseVO updateRechargeStatus(@RequestBody @Valid updateRechargeStatusDTO userUpdate, @PathVariable("id") Integer id) {
        return userService.updateRechargeStatus(userUpdate, id);
    }



}
