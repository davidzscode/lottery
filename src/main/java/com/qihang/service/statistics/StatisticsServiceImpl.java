package com.qihang.service.statistics;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.statistics.app.dto.CountShopPriceDTO;
import com.qihang.controller.statistics.app.vo.CountShopPriceVO;
import com.qihang.controller.statistics.app.vo.YesterdayWinningListVO;
import com.qihang.controller.statistics.vo.StatisticsVO;
import com.qihang.domain.order.LotteryOrderDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.user.UserDO;
import com.qihang.domain.withdrawal.WithdrawalDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderTypeEnum;
import com.qihang.enumeration.user.IsAgentEnum;
import com.qihang.enumeration.withdrawal.ExamineEnum;
import com.qihang.mapper.order.LotteryOrderMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.user.UserMapper;
import com.qihang.mapper.withdrawal.WithdrawalMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-06 13:46
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    
    @Resource
    private LotteryOrderMapper lotteryOrderMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private WithdrawalMapper withdrawalMapper;
    
    @Resource
    private PayOrderMapper payOrderMapper;
    
    @Override
    public StatisticsVO calculation() {
        List<Integer> agentList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getIsAgent, "1")).stream().map(UserDO::getId).collect(Collectors.toList());
        StatisticsVO statistics = new StatisticsVO();
        //待出票数量
        Long ticketingCount = lotteryOrderMapper.selectCount(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_ISSUED.getKey()));
        Long awardCount = lotteryOrderMapper.selectCount(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.WAITING_AWARD.getKey()));
        Long withdrawalCount = withdrawalMapper.selectCount(new QueryWrapper<WithdrawalDO>().lambda().eq(WithdrawalDO::getState, ExamineEnum.UNAPPROVED.getKey()));
        Long userCount = userMapper.selectCount(null);
        Date start = DateUtil.parse(DateUtil.today() + " 00:00:00");
        Date end = DateUtil.parse(DateUtil.today() + " 23:59:59");
        LambdaQueryWrapper<UserDO> qw = new QueryWrapper<UserDO>().lambda()
                .ge(UserDO::getCreateTime, start)
                .lt(UserDO::getCreateTime, end);
        List<UserDO> userList = userMapper.selectList(qw);
        Long registerCount = Long.valueOf(userList.size());
        Long realCount = userList.stream().filter(user -> user.getIsReal().equals("1")).count();
        List<Map<String, Object>> maps = userMapper.selectMaps(new QueryWrapper<UserDO>().select("sum(gold) as totalGold,sum(price) as totalPrice"));
        BigDecimal totalPrice = new BigDecimal(maps.get(0).get("totalGold").toString()).add(new BigDecimal(maps.get(0).get("totalPrice").toString()));
        QueryWrapper<PayOrderDO> payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, start);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, end);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getState, PayOrderStateEnum.PAID.getKey());
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.RECHARGE.getKey());
        payOrderQueryWrapper.lambda().notIn(PayOrderDO::getUserId, agentList);
        List<Map<String, Object>> mapList = payOrderMapper.selectMaps(payOrderQueryWrapper);
        BigDecimal rechargePrice = new BigDecimal(ObjectUtil.isNull(mapList.get(0)) ? "0" : mapList.get(0).get("totalPrice").toString());
        QueryWrapper<WithdrawalDO> withdrawalQueryWrapper = new QueryWrapper<>();
        withdrawalQueryWrapper.select("sum(amount) as totalPrice");
        withdrawalQueryWrapper.lambda().ge(WithdrawalDO::getUpdateTime, start);
        withdrawalQueryWrapper.lambda().le(WithdrawalDO::getUpdateTime, end);
        withdrawalQueryWrapper.lambda().eq(WithdrawalDO::getState, ExamineEnum.PAID.getKey());
        withdrawalQueryWrapper.lambda().notIn(WithdrawalDO::getUserId, agentList);
        List<Map<String, Object>> withdrawalMapList = withdrawalMapper.selectMaps(withdrawalQueryWrapper);
        BigDecimal withdrawalPrice = new BigDecimal(ObjectUtil.isNull(withdrawalMapList.get(0)) ? "0" : withdrawalMapList.get(0).get("totalPrice").toString());
        List<Map<String, Object>> lotteryOrderMapList = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>().select("sum(win_price) as totalPrice").lambda()
                .eq(LotteryOrderDO::getState, LotteryOrderStateEnum.WAITING_AWARD.getKey())
                .notIn(LotteryOrderDO::getUserId, agentList));
        BigDecimal awardPrice = new BigDecimal(ObjectUtil.isNull(lotteryOrderMapList.get(0)) ? "0" : lotteryOrderMapList.get(0).get("totalPrice").toString());
        List<Map<String, Object>> orderMapList = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>().select("sum(price) as totalPrice").lambda()
                .ge(LotteryOrderDO::getCreateTime, start)
                .le(LotteryOrderDO::getCreateTime, end)
                .notIn(LotteryOrderDO::getUserId, agentList)
                .ne(LotteryOrderDO::getState, LotteryOrderStateEnum.REFUSE.getKey())
                .ne(LotteryOrderDO::getState, LotteryOrderStateEnum.REFUND.getKey()));
        BigDecimal bettingPrice = new BigDecimal(ObjectUtil.isNull(orderMapList.get(0)) ? "0" : orderMapList.get(0).get("totalPrice").toString());
        
        List<Map<String, Object>> loMapList = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>().select("sum(win_price) as totalPrice").lambda()
                .eq(LotteryOrderDO::getState, LotteryOrderStateEnum.ALREADY_AWARD.getKey())
                .notIn(LotteryOrderDO::getUserId, agentList));
        BigDecimal alreadyAwardPrice = new BigDecimal(ObjectUtil.isNull(loMapList.get(0)) ? "0" : loMapList.get(0).get("totalPrice").toString());
        
        
        //查询昨天的下注金额和派奖金额
        start = DateUtil.offsetDay(start, -1);
        end = DateUtil.offsetDay(end, -1);
        List<Map<String, Object>> yesterdayBettingPriceMap = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>()
                .select("sum(price) as totalPrice")
                .lambda()
                .ge(LotteryOrderDO::getCreateTime, start)
                .le(LotteryOrderDO::getCreateTime, end)
                .ne(LotteryOrderDO::getState, LotteryOrderStateEnum.REFUSE.getKey())
                .ne(LotteryOrderDO::getState, LotteryOrderStateEnum.REFUND.getKey())
                .notIn(LotteryOrderDO::getUserId, agentList));
        BigDecimal yesterdayBettingPrice = new BigDecimal(ObjectUtil.isNull(yesterdayBettingPriceMap.get(0)) ? "0" : yesterdayBettingPriceMap.get(0).get("totalPrice").toString());
        
        List<Map<String, Object>> yesterdayAwardPriceMap = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>().select("sum(win_price) as totalPrice")
                .lambda()
                .ge(LotteryOrderDO::getCreateTime, start)
                .le(LotteryOrderDO::getCreateTime, end)
                .notIn(LotteryOrderDO::getUserId, agentList)
                .and(lq -> lq.eq(LotteryOrderDO::getState, LotteryOrderStateEnum.WAITING_AWARD.getKey())
                        .or()
                        .eq(LotteryOrderDO::getState, LotteryOrderStateEnum.ALREADY_AWARD.getKey())));
        BigDecimal yesterdayAwardPrice = new BigDecimal(ObjectUtil.isNull(yesterdayAwardPriceMap.get(0)) ? "0" : yesterdayAwardPriceMap.get(0).get("totalPrice").toString());
        
        //总提现金额
        List<Map<String, Object>> yesterdayBettingPriceMaps = withdrawalMapper.selectMaps(new QueryWrapper<WithdrawalDO>()
                .select("sum(amount) as totalPrice")
                .lambda()
                .eq(WithdrawalDO::getState, "1")
                .notIn(WithdrawalDO::getUserId, agentList));
        
        BigDecimal s1 = new BigDecimal(ObjectUtil.isNull(yesterdayBettingPriceMaps.get(0)) ? "0" : yesterdayBettingPriceMaps.get(0).get("totalPrice").toString());
        
        //查找总共充值金额
        List<Map<String, Object>> totalMaps = payOrderMapper.selectMaps(new QueryWrapper<PayOrderDO>().select("sum(price) as totalPrice")
                .lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.RECHARGE.getKey())
                .eq(PayOrderDO::getState, PayOrderStateEnum.PAID.getKey())
                .notIn(PayOrderDO::getUserId, agentList));
        BigDecimal s3 = new BigDecimal(ObjectUtil.isNull(totalMaps.get(0)) ? "0" : totalMaps.get(0).get("totalPrice").toString());
        
        //总投注
        List<Map<String, Object>> totalBettingPriceMaps = lotteryOrderMapper.selectMaps(new QueryWrapper<LotteryOrderDO>()
                .select("sum(price) as totalPrice")
                .lambda()
                .ne(LotteryOrderDO::getState, "5").ne(LotteryOrderDO::getState, "6")
                .notIn(LotteryOrderDO::getUserId, agentList));
        BigDecimal s2 = new BigDecimal(ObjectUtil.isNull(totalBettingPriceMaps.get(0)) ? "0" : totalBettingPriceMaps.get(0).get("totalPrice").toString());
        
        
        //总充值
        statistics.setTotalRechargePrice(s3);
        
        //总投注
        statistics.setTotalBetting(s2);
        
        //总盈利
        statistics.setTotalProfitPrice(s3.subtract(s1));
        
        //昨日派奖金额
        statistics.setYesterdayAwardPrice(yesterdayAwardPrice);
        //昨日盈利金额
        statistics.setYesterdayProfitPrice(yesterdayBettingPrice.subtract(yesterdayAwardPrice));
        //昨日投注金额
        statistics.setYesterdayBettingPrice(yesterdayBettingPrice);
        statistics.setTicketingCount(ticketingCount);
        
        statistics.setAwardCount(awardCount);
        statistics.setWithdrawalCount(withdrawalCount);
        statistics.setUserCount(userCount);
        statistics.setRegisterCount(registerCount);
        statistics.setRealCount(realCount);
        statistics.setTotalPrice(totalPrice);
        statistics.setRechargePrice(rechargePrice);
        statistics.setWithdrawalPrice(withdrawalPrice);
        statistics.setAwardPrice(awardPrice);
        statistics.setBettingPrice(bettingPrice);
        statistics.setAlreadyAwardPrice(alreadyAwardPrice);
        return statistics;
    }
    
    /**
     * 统计店铺金额
     * @param countShopPriceDTO
     * @param shopkeeperId
     */
    @Override
    public CountShopPriceVO countShopPrice(CountShopPriceDTO countShopPriceDTO, Integer shopkeeperId) {
        Integer tenantId = countShopPriceDTO.getTenantId();
        CountShopPriceVO count = new CountShopPriceVO();
        if (shopkeeperId != null) {
            UserDO shopkeeper = userMapper.selectById(shopkeeperId);
            if (!shopkeeper.getIsAgent().equals(IsAgentEnum.shopkeeper.getKey())) {
                CountShopPriceVO countShopPriceVO = new CountShopPriceVO();
                countShopPriceVO.setSuccess(false);
                countShopPriceVO.setErrorCode(ErrorCodeEnum.E107.getKey());
                countShopPriceVO.setErrorMsg(ErrorCodeEnum.E107.getValue());
                return countShopPriceVO;
            }
        }
        List<UserDO> userList = userMapper.selectList(new QueryWrapper<UserDO>().lambda()
                .eq(shopkeeperId != null, UserDO::getPpid, shopkeeperId)
                .eq(tenantId != null, UserDO::getTenantId, tenantId)
                .eq(UserDO::getIsAgent, IsAgentEnum.User.getKey())
                .eq(UserDO::getMoni, 0));
        
        List<Integer> userIdList = userList.stream().map(UserDO::getId).collect(Collectors.toList());
        if (userIdList.isEmpty()) {
            return count;
        }
        Date startTime;
        if (countShopPriceDTO.getStartTime() == null) {
            startTime = DateUtil.parse(DateUtil.today() + " 00:00:00");
        } else {
            startTime = countShopPriceDTO.getStartTime();
        }
        Date endTime;
        if (countShopPriceDTO.getEndTime() == null) {
            endTime = DateUtil.parse(DateUtil.today() + " 23:59:59");
        } else {
            endTime = countShopPriceDTO.getEndTime();
        }
        // 出票总金额( lottery_order表   state=1234 的金额)
        QueryWrapper<LotteryOrderDO> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.select("sum(price) as totalPrice");
        orderQueryWrapper.lambda().ge(LotteryOrderDO::getCreateTime, startTime);
        orderQueryWrapper.lambda().le(LotteryOrderDO::getCreateTime, endTime);
        orderQueryWrapper.lambda().in(LotteryOrderDO::getUserId, userIdList);
        List<String> stateList = new ArrayList<>();
        stateList.add(LotteryOrderStateEnum.TO_BE_AWARDED.getKey());
        stateList.add(LotteryOrderStateEnum.FAIL_TO_WIN.getKey());
        stateList.add(LotteryOrderStateEnum.WAITING_AWARD.getKey());
        stateList.add(LotteryOrderStateEnum.ALREADY_AWARD.getKey());
        orderQueryWrapper.lambda().in(LotteryOrderDO::getState, stateList);
        List<Object> sumPrice = lotteryOrderMapper.selectObjs(orderQueryWrapper);
        count.setTotalOutTicketPrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        
        // 中奖总金额(lottery_order表   state=4 的金额 )
//        orderQueryWrapper = new QueryWrapper<>();
//        orderQueryWrapper.select("sum(price) as totalPrice");
//        orderQueryWrapper.lambda().ge(LotteryOrderDO::getUpdateTime, startTime);
//        orderQueryWrapper.lambda().le(LotteryOrderDO::getUpdateTime, endTime);
//        orderQueryWrapper.lambda().in(LotteryOrderDO::getUserId, userIdList);
//        orderQueryWrapper.lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.ALREADY_AWARD.getKey());
//        sumPrice = lotteryOrderMapper.selectObjs(orderQueryWrapper);
        QueryWrapper<PayOrderDO> payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, startTime);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, endTime);
        payOrderQueryWrapper.lambda().in(PayOrderDO::getUserId, userIdList);
        List<String> typeList = new ArrayList<>();
        typeList.add(PayOrderTypeEnum.FOOTBALL_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.BASKETBALL_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.SINGLE_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.ARRAY_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.ARRANGE_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.SEVEN_STAR_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.VICTORY_DEFEAT_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.REN_JIU_AWARD.getKey());
        typeList.add(PayOrderTypeEnum.GRAND_LOTTO_AWARD.getKey());
        payOrderQueryWrapper.lambda().in(PayOrderDO::getType, typeList);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getState, PayOrderStateEnum.PAYMENT.getKey());
        sumPrice = payOrderMapper.selectObjs(payOrderQueryWrapper);
        count.setTotalWinningPrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        
        // 充值总金额(pay_order 表 type=99)
        payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, startTime);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, endTime);
        payOrderQueryWrapper.lambda().in(PayOrderDO::getUserId, userIdList);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.AUTO_RECHARGE.getKey());
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getState, PayOrderStateEnum.PAID.getKey());
        sumPrice = payOrderMapper.selectObjs(payOrderQueryWrapper);
        count.setTotalRechargePrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        
        // 提现总金额( pay_order 表type=98)
        payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, startTime);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, endTime);
        payOrderQueryWrapper.lambda().in(PayOrderDO::getUserId, userIdList);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.USER_PAYMENT.getKey());
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getState, PayOrderStateEnum.PAYMENT.getKey());
        sumPrice = payOrderMapper.selectObjs(payOrderQueryWrapper);
        count.setTotalWithdrawalPrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        
        // 加款总金额( pay_order 表  type=0)
        payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, startTime);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, endTime);
        payOrderQueryWrapper.lambda().in(PayOrderDO::getUserId, userIdList);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.RECHARGE.getKey());
        sumPrice = payOrderMapper.selectObjs(payOrderQueryWrapper);
        count.setTotalAddFundsPrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        
        // 扣款总金额( pay_order 表  type=5)
        payOrderQueryWrapper = new QueryWrapper<>();
        payOrderQueryWrapper.select("sum(price) as totalPrice");
        payOrderQueryWrapper.lambda().ge(PayOrderDO::getCreateTime, startTime);
        payOrderQueryWrapper.lambda().le(PayOrderDO::getCreateTime, endTime);
        payOrderQueryWrapper.lambda().in(PayOrderDO::getUserId, userIdList);
        payOrderQueryWrapper.lambda().eq(PayOrderDO::getType, PayOrderTypeEnum.PAYMENT.getKey());
        sumPrice = payOrderMapper.selectObjs(payOrderQueryWrapper);
        count.setTotalDeductionPrice(ObjectUtil.isNotNull(sumPrice.get(0)) ? new BigDecimal(sumPrice.get(0).toString()) : new BigDecimal(0));
        return count;
    }
    
    /**
     * 昨日中奖榜单
     * 查询lottery_order表  昨天中奖最多的三个用户  要用户昵称和中奖金额
     */
    @Override
    public CommonListVO<YesterdayWinningListVO> yesterdayWinningList() {
        CommonListVO<YesterdayWinningListVO> commonListVO = new CommonListVO<>();
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000);
        today = DateUtil.parse(DateUtil.format(today, "yyyy-MM-dd"), "yyyy-MM-dd");
        yesterday = DateUtil.parse(DateUtil.format(yesterday, "yyyy-MM-dd"), "yyyy-MM-dd");
        QueryWrapper<LotteryOrderDO> lotteryQueryWrapper = new QueryWrapper<>();
        lotteryQueryWrapper.select("user_id,sum(win_price) as totalPrice");
        lotteryQueryWrapper.lambda().ge(LotteryOrderDO::getCreateTime, yesterday);
        lotteryQueryWrapper.lambda().le(LotteryOrderDO::getCreateTime, today);
        lotteryQueryWrapper.lambda().groupBy(LotteryOrderDO::getUserId);
        lotteryQueryWrapper.orderByDesc("totalPrice");
        List<Map<String, Object>> mapList = lotteryOrderMapper.selectMaps(lotteryQueryWrapper);
        List<YesterdayWinningListVO> resList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            YesterdayWinningListVO vo = new YesterdayWinningListVO();
            vo.setUserId((Integer) map.get("user_id"));
            vo.setTotalWinningPrice(ObjectUtil.isNotNull(map.get("totalPrice")) ? new BigDecimal(map.get("totalPrice").toString()) : new BigDecimal(0));
            resList.add(vo);
        }
        List<Integer> userIds = resList.stream().map(YesterdayWinningListVO::getUserId).collect(Collectors.toList());
        if (!userIds.isEmpty()) {
            Map<Integer, UserDO> nameMap = userMapper.selectList(new QueryWrapper<UserDO>().lambda().in(UserDO::getId, userIds))
                    .stream().collect(Collectors.toMap(UserDO::getId, UserDO -> UserDO));
            for (YesterdayWinningListVO vo : resList) {
                vo.setUserNickName(nameMap.get(vo.getUserId()).getNickname());
                vo.setAvatar(nameMap.get(vo.getUserId()).getAvatar());
            }
        }
        commonListVO.setVoList(resList);
        commonListVO.setTotal((long) resList.size());
        return commonListVO;
    }
}
