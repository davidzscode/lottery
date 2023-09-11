package com.qihang.service.shop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.code.QrCodeUtil;
import com.qihang.common.util.nickname.NameUtil;
import com.qihang.common.util.security.BcryptPasswordUtil;
import com.qihang.common.util.upload.S3Util;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.ballgame.admin.vo.BallGameWithTenantVO;
import com.qihang.controller.dict.vo.SysDictVO;
import com.qihang.controller.shop.admin.dto.*;
import com.qihang.controller.shop.admin.vo.AdminShopVO;
import com.qihang.controller.shop.admin.vo.ShopDetailVO;
import com.qihang.controller.shop.app.dto.ShopDTO;
import com.qihang.controller.shop.app.vo.ShopVO;
import com.qihang.domain.ballgame.BallGameDO;
import com.qihang.domain.dict.SysDictDO;
import com.qihang.domain.documentary.DocumentaryDO;
import com.qihang.domain.documentary.DocumentaryUserDO;
import com.qihang.domain.follow.FollowDO;
import com.qihang.domain.log.LogDO;
import com.qihang.domain.notice.NoticeDO;
import com.qihang.domain.order.LotteryOrderDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.permutation.PermutationDO;
import com.qihang.domain.racingball.RacingBallDO;
import com.qihang.domain.realm.SysDomainDO;
import com.qihang.domain.shop.ShopDO;
import com.qihang.domain.user.SysUserDO;
import com.qihang.domain.user.UserDO;
import com.qihang.domain.withdrawal.WithdrawalDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.line.LineEnum;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.enumeration.user.SexEnum;
import com.qihang.mapper.ballgame.BallGameMapper;
import com.qihang.mapper.dict.SysDictMapper;
import com.qihang.mapper.documentary.DocumentaryMapper;
import com.qihang.mapper.documentary.DocumentaryUserMapper;
import com.qihang.mapper.follow.FollowMapper;
import com.qihang.mapper.log.LogMapper;
import com.qihang.mapper.notice.NoticeMapper;
import com.qihang.mapper.order.LotteryOrderMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.permutation.PermutationMapper;
import com.qihang.mapper.racingball.RacingBallMapper;
import com.qihang.mapper.realm.SysDomainMapper;
import com.qihang.mapper.shop.ShopMapper;
import com.qihang.mapper.user.SysUserMapper;
import com.qihang.mapper.user.UserMapper;
import com.qihang.mapper.withdrawal.WithdrawalMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author qihang
 * @since 2022-11-13
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopDO> implements IShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private LogMapper logMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SysDictMapper sysDictMapper;

    @Resource
    private PayOrderMapper payOrderMapper;

    @Resource
    private DocumentaryMapper documentaryMapper;

    @Resource
    private DocumentaryUserMapper documentaryUserMapper;

    @Resource
    private PermutationMapper permutationMapper;

    @Resource
    private WithdrawalMapper withdrawalMapper;

    @Resource
    private FollowMapper followMapper;

    @Resource
    private LotteryOrderMapper lotteryOrderMapper;

    @Resource
    private BallGameMapper ballGameMapper;

    @Resource
    private RacingBallMapper racingBallMapper;

    @Resource
    private NoticeMapper noticeMapper;


    @Resource
    private SysDomainMapper sysDomainMapper;

    @Resource
    private S3Util s3Util;

    @Override
    @TenantIgnore
    public CommonListVO<ShopVO> shopList(ShopDTO shop) {
        CommonListVO<ShopVO> commonList = new CommonListVO<>();
        if (StrUtil.isNotBlank(shop.getPhone())) {
            List<UserDO> userList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getPhone, shop.getPhone()));
            if (userList == null || userList.isEmpty()) {
                userList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getNickname, shop.getPhone()));
            }
            List<ShopVO> shopList = new ArrayList<>();
            for (UserDO userDO : userList) {
                ShopDO shopDO = shopMapper.selectOne(new QueryWrapper<ShopDO>().lambda().eq(ShopDO::getLine, LineEnum.UPPER.getKey()).eq(ShopDO::getId, userDO.getTenantId()));
                ShopVO shopVO = new ShopVO();
                if (ObjectUtil.isNotNull(shopDO)) {
                    BeanUtils.copyProperties(shopDO, shopVO);
                }
                shopList.add(shopVO);
            }
            commonList.setVoList(shopList);
        } else {
            List<ShopDO> shopList = shopMapper.selectList(new QueryWrapper<ShopDO>().lambda().eq(ShopDO::getLine, LineEnum.UPPER.getKey()));
            List<ShopVO> list = BeanUtil.copyToList(shopList, ShopVO.class);
            commonList.setVoList(list);
        }
        return commonList;
    }

    @Override
    @TenantIgnore
    public CommonListVO<AdminShopVO> adminShopList(AdminShopDTO adminShop) {
        CommonListVO<AdminShopVO> commonList = new CommonListVO<>();
        //分页
        Page<ShopDO> page = new Page<>(adminShop.getPageNo(), adminShop.getPageSize());
        LambdaQueryWrapper<ShopDO> qw = new QueryWrapper<ShopDO>().lambda();
        qw.like(StrUtil.isNotBlank(adminShop.getName()), ShopDO::getName, adminShop.getName());

        Page<ShopDO> pageData = shopMapper.selectPage(page, qw);
        List<ShopDO> list = pageData.getRecords();
        List<AdminShopVO> shopList = BeanUtil.copyToList(list, AdminShopVO.class);
        if (!shopList.isEmpty()) {
            List<Integer> shopIds = shopList.stream().map(AdminShopVO::getId).collect(Collectors.toList());
            List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().lambda()
                    .in(UserDO::getTenantId, shopIds).eq(UserDO::getIsAgent, IsAgentEnum.shopkeeper.getKey()));
            Map<Integer, List<UserDO>> mapByTenantId = userDOList.stream().collect(Collectors.groupingBy(UserDO::getTenantId));
            for (AdminShopVO shopVO : shopList) {
                List<SysUserDO> sysUserList = sysUserMapper.selectList(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getTenantId, shopVO.getId()));
                if (CollUtil.isNotEmpty(sysUserList)) {
                    shopVO.setUsername(sysUserList.get(0).getUsername());
                }
                List<UserDO> userDOS = mapByTenantId.get(shopVO.getId());
                if (CollUtil.isNotEmpty(sysUserList)) {
                    shopVO.setShopkeeperId(userDOS.get(0).getId());
                    shopVO.setShopkeeperNickname(userDOS.get(0).getNickname());
                }
            }
        }
        commonList.setVoList(shopList);
        commonList.setTotal(pageData.getTotal());
        return commonList;
    }

    @Override
    public BaseVO updateLine(Integer id, String type) {
        ShopDO shop = new ShopDO();
        shop.setId(id);
        shop.setLine(type);
        int i = shopMapper.updateById(shop);
        return new BaseVO(i > 0, null, null);
    }

    @Override
    @TenantIgnore
    @Transactional(rollbackFor = Exception.class)
    public BaseVO add(AddShopDTO addShop, Integer userId) {
        SysUserDO user = sysUserMapper.selectOne(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getUsername, addShop.getSysUsername()));
        if (ObjectUtil.isNotNull(user)) {
            return new BaseVO(false, ErrorCodeEnum.E090.getKey(), ErrorCodeEnum.E090.getValue());
        }
        ShopDO shop = new ShopDO();
        BeanUtils.copyProperties(addShop, shop);
        shop.setCreateTime(new Date());
        shop.setUpdateTime(new Date());
        shopMapper.insert(shop);
        //添加系统用户
        SysUserDO sysUserDO = sysUserMapper.selectById(userId);
        SysUserDO sysUser = new SysUserDO();
        sysUser.setUsername(addShop.getSysUsername());
        sysUser.setPassword(BcryptPasswordUtil.createBCryptPassword(addShop.getSysPassword()));
        sysUser.setEmail(addShop.getEmail());
        sysUser.setName(shop.getName() + "管理员");
        sysUser.setPayPwd(addShop.getPayPwd());
        sysUser.setTenantId(shop.getId());
        sysUser.setCreateUser(sysUserDO.getUsername());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setLastLoginDate(new Date());
        sysUser.setUpdateUser(sysUserDO.getUsername());
        sysUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
        sysUserMapper.insert(sysUser);
        //将t_ball_game 和t_notice数据复制一份  TenantId直接写死1
        List<BallGameDO> ballGameList = ballGameMapper.selectList(new QueryWrapper<BallGameDO>().lambda().eq(BallGameDO::getTenantId, "1"));
        for (BallGameDO ballGameDO : ballGameList) {
            BallGameDO ballGame = new BallGameDO();
            ballGame.setTenantId(shop.getId());
            ballGame.setName(ballGameDO.getName());
            ballGame.setUrl(ballGameDO.getUrl());
            ballGame.setLine(ballGameDO.getLine());
            ballGame.setCreateTime(new Date());
            ballGame.setUpdateTime(new Date());
            ballGameMapper.insert(ballGame);
        }
        List<NoticeDO> noticeList = noticeMapper.selectList(new QueryWrapper<NoticeDO>().lambda().eq(NoticeDO::getTenantId, "1"));
        for (NoticeDO noticeDO : noticeList) {
            NoticeDO notice = new NoticeDO();
            notice.setTenantId(shop.getId());
            notice.setMsg(noticeDO.getMsg());
            notice.setCreateTime(new Date());
            notice.setUpdateTime(new Date());
            noticeMapper.insert(notice);
        }

        //添加app用户
        //九位数的uid
        int uid = (int) (Math.random() * (400000000 - 100000000)) + 100000000;
        UserDO userDO = new UserDO();
        userDO.setUid(uid);
        userDO.setPhone(addShop.getAppPhone());
        userDO.setPassword(BcryptPasswordUtil.createBCryptPassword(addShop.getAppPassword()));
        userDO.setSex(SexEnum.UNKNOWN.getKey());
        userDO.setNickname(NameUtil.getName());
        userDO.setGold(new BigDecimal(0));
        userDO.setPrice(new BigDecimal(0));
        userDO.setIsAgent("1");
        userDO.setCreateTime(new Date());
        userDO.setTenantId(shop.getId());
        userDO.setUpdateTime(new Date());
        SysDomainDO sysDomain = sysDomainMapper.selectOne(null);
        InputStream is = QrCodeUtil.generate(sysDomain.getAppUrl() + "/#/pages/user/register?uid=" + uid);
        String codeUrl = s3Util.upload(is);
        userDO.setQrCode(codeUrl);
        userDO.setAvatar("https://img-blog.csdnimg.cn/142cf2dca89e456aa3fe3aacdf688363.jpeg");
        userMapper.insert(userDO);
        return new BaseVO();
    }

    @Override
    public BaseVO recharge(ShopRechargeDTO shopRecharge) {
        ShopDO shop = shopMapper.selectById(shopRecharge.getId());
        shop.setBalance(shop.getBalance().add(shopRecharge.getBalance()));
        int i = shopMapper.updateById(shop);
        return new BaseVO(i > 0, null, null);
    }

    @Override
    @TenantIgnore
    public ShopDO getShopById(Integer id) {
        return shopMapper.selectById(id);
    }

    @Override
    @TenantIgnore
    public BaseVO delete(Integer id) {
        shopMapper.deleteById(id);
        ballGameMapper.delete(new QueryWrapper<BallGameDO>().lambda().eq(BallGameDO::getTenantId, id));
        noticeMapper.delete(new QueryWrapper<NoticeDO>().lambda().eq(NoticeDO::getTenantId, id));
        sysDictMapper.delete(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getTenantId, id));
        sysUserMapper.delete(new QueryWrapper<SysUserDO>().lambda().eq(SysUserDO::getTenantId, id));
        withdrawalMapper.delete(new QueryWrapper<WithdrawalDO>().lambda().eq(WithdrawalDO::getTenantId, id));
        payOrderMapper.delete(new QueryWrapper<PayOrderDO>().lambda().eq(PayOrderDO::getTenantId, id));
        permutationMapper.delete(new QueryWrapper<PermutationDO>().lambda().eq(PermutationDO::getTenantId, id));


        List<LotteryOrderDO> orderList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getTenantId, id));
        for (LotteryOrderDO lotteryOrder : orderList) {
            documentaryMapper.delete(new QueryWrapper<DocumentaryDO>().lambda().eq(DocumentaryDO::getLotteryOrderId, lotteryOrder.getId()));
            documentaryUserMapper.delete(new QueryWrapper<DocumentaryUserDO>().lambda().eq(DocumentaryUserDO::getLotteryOrderId, lotteryOrder.getId()));
            lotteryOrderMapper.deleteById(lotteryOrder.getId());
        }
        racingBallMapper.delete(new QueryWrapper<RacingBallDO>().lambda().eq(RacingBallDO::getTenantId, id));

        List<UserDO> userList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getTenantId, id));
        for (UserDO user : userList) {
            followMapper.delete(new QueryWrapper<FollowDO>().lambda().eq(FollowDO::getUserId, user.getId()).or().eq(FollowDO::getTargetId, user.getId()));
            userMapper.delete(new QueryWrapper<UserDO>().lambda().eq(UserDO::getTenantId, id).eq(UserDO::getIsAgent, 1));
            logMapper.delete(new QueryWrapper<LogDO>().lambda().eq(LogDO::getUserId, user.getId()));
        }

        UserDO user = new UserDO();
        user.setTenantId(1);
        user.setPpid(1);
        userMapper.update(user, new QueryWrapper<UserDO>().lambda().eq(UserDO::getTenantId, id).eq(UserDO::getIsAgent, 0));
        user.setPid(1);
        userMapper.update(user, new QueryWrapper<UserDO>().lambda().eq(UserDO::getTenantId, id).eq(UserDO::getIsAgent, 2));

        return new BaseVO();
    }

    /**
     * 设置店铺扣款比例
     *
     * @param dto
     */
    @Override
    public BaseVO editShopRate(EditShopRateDTO dto) {
        ShopDO shop = shopMapper.selectById(dto.getId());
        shop.setShopRate(dto.getShopRate());
        int i = shopMapper.updateById(shop);
        return new BaseVO(i > 0, null, null);
    }

    /**
     * 获取店铺详情
     *
     * @param dto
     */
    @Override
    public ShopDetailVO shopDetail(ShopDetailDTO dto) {
        ShopDetailVO vo = new ShopDetailVO();
        ShopDO shop = shopMapper.selectById(dto.getId());
        if (shop == null) {
            return new ShopDetailVO(false, null, "店铺不存在");
        }
        BeanUtils.copyProperties(shop, vo);
        //充值通道
        List<SysDictDO> dictDoList = sysDictMapper.selectList(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getTenantId, dto.getId()));
        List<SysDictVO> dictList = BeanUtil.copyToList(dictDoList, SysDictVO.class);
        vo.setChannels(dictList);
        //游戏配置
        List<BallGameDO> ballGameList = ballGameMapper.selectList(new QueryWrapper<BallGameDO>().lambda().eq(BallGameDO::getTenantId, vo.getId()));
        List<BallGameWithTenantVO> ballGameWithTenantVOS = BeanUtil.copyToList(ballGameList, BallGameWithTenantVO.class);
        vo.setBallGames(ballGameWithTenantVOS);
        return vo;
    }

    /**
     * 店铺上架下架
     *
     * @param dto
     */
    @Override
    public BaseVO editShopLine(EditShopLineDTO dto) {
        ShopDO shop = shopMapper.selectById(dto.getId());
        shop.setLine(dto.getLine());
        int i = shopMapper.updateById(shop);
        return new BaseVO(i > 0, null, null);

    }

}
