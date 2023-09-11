package com.qihang.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.dto.PageDTO;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.sys.dto.ShopkeeperUserQueryDTO;
import com.qihang.controller.sys.vo.ShopkeeperUserQueryVO;
import com.qihang.controller.user.admin.dto.*;
import com.qihang.controller.user.admin.vo.AdminStatisticsVO;
import com.qihang.controller.user.admin.vo.GetUserDetailVO;
import com.qihang.controller.user.admin.vo.UserQueryVO;
import com.qihang.controller.user.app.dto.*;
import com.qihang.controller.user.app.vo.*;
import com.qihang.domain.user.UserDO;

import javax.validation.Valid;

/**
 * @author: qihang
 * @description:
 * @time: 2022-06-28 10:09
 */
public interface IUserService extends IService<UserDO> {
    /**
     * 登录
     * @param userLoginDTO
     */
    BaseVO login(UserLoginDTO userLoginDTO);
    
    /**
     * 刷新token
     * @param userRefreshDTO
     */
    BaseVO refresh(UserRefreshDTO userRefreshDTO);
    
    
    /**
     * 发送验证码
     * @param phoneDTO
     * @param ip
     */
    BaseVO send(PhoneDTO phoneDTO, String ip);
    
    /**
     * 注册
     * @param registerDTO
     * @throws Exception
     */
    BaseVO register(RegisterDTO registerDTO) throws Exception;
    
    
    /**
     * 修改密码
     * @param changePwdDTO
     */
    BaseVO changePwd(ChangePwdDTO changePwdDTO);
    
    /**
     * 修改用户信息
     * @param changeUserDTO
     */
    BaseVO changeUser(ChangeUserDTO changeUserDTO);
    
    
    /**
     * 实名认证
     * @param realDTO
     * @param userId
     */
    BaseVO real(RealDTO realDTO, Integer userId);
    
    /**
     * 获取用户信息
     * @param userId
     */
    UserVO getUser(Integer userId);
    
    
    /**
     * 后台用户列表
     * @param userQueryDTO
     */
    CommonListVO<UserQueryVO> pageList(UserQueryDTO userQueryDTO);
    
    
    /**
     * 获取下级代理接口
     * @param userId
     * @param userAgent
     */
    CommonListVO<AgentVO> agent(UserAgentDTO userAgent, Integer userId);
    
    AgentSumVO agentSalesperson(@Valid AgentSalespersonDTO userAgent, Integer userId);
    
    /**
     * 后台删除
     * @param id
     */
    BaseVO delete(Integer id);
    
    /**
     * 禁用启用状态
     * @param userUpdate
     * @param id
     */
    BaseVO updateStatus(UserUpdateDTO userUpdate, Integer id);
    
    /**
     * 加减钱
     * @param userRecharge
     * @param id
     */
    BaseVO recharge(UserRechargeDTO userRecharge, Integer id);
    
    /**
     * 后台修改用户密码接口
     * @param adminChangePwd
     */
    BaseVO adminChangePwd(AdminChangePwdDTO adminChangePwd);
    
    /**
     * 用户统计
     * @param userStatistics
     */
    AdminStatisticsVO userStatistics(UserStatisticsDTO userStatistics);
    
    /**
     * 根据昵称获取用户信息
     * @param userByNickName
     */
    CommonListVO<UserVO> getUserByNickName(UserByNickNameDTO userByNickName);
    
    
    /**
     * 根据用户id获取租户id
     * @param uid
     */
    UserVO getTenantId(Integer uid);
    
    
    /**
     * 提现账户绑定
     * @param binding
     * @param userId
     */
    BaseVO binding(BindingDTO binding, Integer userId);
    
    
    /**
     * 校验手机号不能串店铺
     * @param checkPhone
     */
    BaseVO checkPhone(CheckPhoneDTO checkPhone);
    
    
    /**
     * 校验手机是否已注册
     * @param phone
     */
    BaseVO checkPhoneIsExist(PhoneDTO phone);
    
    
    /**
     * 后台添加用户
     * @param userAdd
     * @param userId
     */
    BaseVO addUser(UserAddDTO userAdd, Integer userId);
    
    BaseVO addSalesperson(AddSalespersonDTO registerDTO, Integer userId) throws Exception;
    
    BaseVO deleteSalesperson(DeleteSalespersonDTO registerDTO, Integer valueOf);
    
    BaseVO shopkeeperRecharge(UserRechargeDTO userRecharge, Integer id, Integer valueOf);
    
    CommonListVO<AgentUsersVO> agentUsers(@Valid UserAgentDTO userAgent, Integer valueOf);
    
    CommonListVO<AgentBettingVO> agentBettingList(@Valid UserAgentDTO userAgent, Integer valueOf);
    
    CommonListVO<MoniUserQueryVO> queryMoniUserList(PageDTO pageDTO, Integer valueOf);
    
    BaseVO createMoniUser(AddSalespersonDTO addSalespersonDTO, Integer valueOf);
    
    BaseVO deleteMoniUser(DeleteMoniUserDTO deleteMoniUserDTO, Integer valueOf);
    
    CommonListVO<ShopkeeperUserQueryVO> shopkeeperList(ShopkeeperUserQueryDTO queryDTO);
    
    CommonListVO<GetAllSaleVO> getAllUser(GetAllSaleDTO userQueryDTO, String isAgent);
    
    GetUserDetailVO getUserDetail(GetUserDetailDTO userQueryDTO);
    
    BaseVO setUserAsSalesperson(SetSalespersonDTO dto);
    
    BaseVO setUserAsMoni(SetSalespersonDTO dto);
    
    BaseVO setUserCommissionRate(SetUserCommissionRateDTO dto);
    
    BaseVO setSalespersonSaleRate(SetSalespersonSaleRateDTO dto);


    BaseVO updateUserStarStatus(updateUserStarStatusDTO userUpdate, Integer id);

    BaseVO updateRechargeStatus(updateRechargeStatusDTO userUpdate, Integer id);
}
