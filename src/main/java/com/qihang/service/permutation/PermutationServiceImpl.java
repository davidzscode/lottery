package com.qihang.service.permutation;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.log.LogUtil;
import com.qihang.common.util.order.OrderNumberGenerationUtil;
import com.qihang.common.util.reward.GrandLottoUtil;
import com.qihang.common.util.reward.PermutationUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.grandlotto.dto.GrandLottoObjDTO;
import com.qihang.controller.permutation.app.dto.PlaceOrderDTO;
import com.qihang.controller.permutation.app.vo.PermutationRecordVO;
import com.qihang.controller.permutation.app.vo.PermutationVO;
import com.qihang.controller.permutation.app.vo.PlaceOrderVO;
import com.qihang.domain.order.LotteryOrderDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.permutation.PermutationAwardDO;
import com.qihang.domain.permutation.PermutationDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderStateEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayTypeEnum;
import com.qihang.mapper.order.LotteryOrderMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.permutation.PermutationAwardMapper;
import com.qihang.mapper.permutation.PermutationMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author qihang
 * @since 2022-10-10
 */
@Service
public class PermutationServiceImpl extends ServiceImpl<PermutationMapper, PermutationDO> implements IPermutationService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LotteryOrderMapper orderMapper;

    @Resource
    private PermutationMapper permutationMapper;

    @Resource
    private PermutationAwardMapper permutationAwardMapper;

    @Resource
    private PayOrderMapper payOrderMapper;


    @Resource
    private LogUtil logUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO placeOrder(List<PlaceOrderDTO> placeList, Integer userId, String type) {
        int hour = DateUtil.hour(new Date(), true);
        if (hour >= 21 && hour <= 22) {
            return new BaseVO(false, ErrorCodeEnum.E082.getKey(), ErrorCodeEnum.E082.getValue());
        }
        PlaceOrderVO placeOrder = new PlaceOrderVO();
        //计算需要下注的金额
        BigDecimal price = new BigDecimal(0);
        for (PlaceOrderDTO placeOrderDTO : placeList) {
            price = price.add(BigDecimal.valueOf(placeOrderDTO.getNotes() * placeOrderDTO.getTimes() * 2));
        }
        //计算用户的账号是否充足
        UserDO user = userMapper.selectById(userId);
        if (price.compareTo(user.getGold()) == 1) {
            if (price.compareTo(user.getGold().add(user.getPrice())) == 1) {
                return new BaseVO(false, ErrorCodeEnum.E0763.getKey(), ErrorCodeEnum.E0763.getValue());
            }
        }
        if (price.compareTo(user.getGold()) == 1) {
            //直接扣除彩金
            BigDecimal remainingPrice = price.subtract(user.getGold());
            //彩金直接设置为0
            user.setGold(new BigDecimal(0));
            //剩下的扣除奖金余额
            user.setPrice(user.getPrice().subtract(remainingPrice));
            userMapper.updateById(user);
        } else {
            //直接扣除彩金
            user.setGold(user.getGold().subtract(price));
            userMapper.updateById(user);
        }
        logUtil.record(LotteryOrderTypeEnum.valueOFS(type).getValue() + "下单,下单金额【" + price + "】");
        //添加钱包消费记录
        PayOrderDO payOrder = new PayOrderDO();
        payOrder.setOrderId(OrderNumberGenerationUtil.getOrderId());
        payOrder.setState(PayOrderStateEnum.PAID.getKey());
        payOrder.setCreateTime(new Date());
        payOrder.setUpdateTime(new Date());
        payOrder.setType(PayOrderTypeEnum.ARRAY.getKey());
        if (type.equals(LotteryOrderTypeEnum.ARRANGE.getKey())) {
            payOrder.setType(PayOrderTypeEnum.ARRANGE.getKey());
        } else if (type.equals(LotteryOrderTypeEnum.SEVEN_STAR.getKey())) {
            payOrder.setType(PayOrderTypeEnum.SEVEN_STAR.getKey());
        } else if (type.equals(LotteryOrderTypeEnum.GRAND_LOTTO.getKey())) {
            payOrder.setType(PayOrderTypeEnum.GRAND_LOTTO.getKey());
        }
        payOrder.setUserId(userId);
        payOrder.setPayType(PayTypeEnum.APP.getKey());
        payOrder.setPrice(price);
        payOrderMapper.insert(payOrder);

        // 1.先查询出奖的最后一条数据从而得出这次买的是第几期
        PermutationAwardDO permutationAward = permutationAwardMapper.selectOne(new QueryWrapper<PermutationAwardDO>().lambda().eq(PermutationAwardDO::getType, type).orderByDesc(PermutationAwardDO::getCreateTime).last("limit 1"));
        //批量添加投注数据
        List<Integer> ids = new ArrayList<>();

        //组合
        List<PermutationVO> list = new ArrayList<>();

        for (PlaceOrderDTO placeOrderDTO : placeList) {
            // 2.添加下注数据
            PermutationDO permutation = new PermutationDO();
            permutation.setUserId(userId);
            permutation.setMode(placeOrderDTO.getMode());
            permutation.setStageNumber(permutationAward.getStageNumber() + 1);
            permutation.setHundredMyriad(StrUtil.join(",", placeOrderDTO.getHundredMyriad()));
            permutation.setTenMyriad(StrUtil.join(",", placeOrderDTO.getTenMyriad()));
            permutation.setMyriad(StrUtil.join(",", placeOrderDTO.getMyriad()));
            permutation.setKilo(StrUtil.join(",", placeOrderDTO.getKilo()));
            permutation.setIndividual(StrUtil.join(",", placeOrderDTO.getIndividual()));
            permutation.setTen(StrUtil.join(",", placeOrderDTO.getTen()));
            permutation.setHundred(StrUtil.join(",", placeOrderDTO.getHundred()));
            //处理组三和组六
            if (permutation.getMode().equals("1") || permutation.getMode().equals("2")) {
                permutation.setIndividual(JSONUtil.toJsonStr(placeOrderDTO.getIndividual()));
            }
            if (StrUtil.equals(type, LotteryOrderTypeEnum.GRAND_LOTTO.getKey())) {
                permutation.setIndividual(JSONUtil.toJsonStr(placeOrderDTO.getIndividual()));
                permutation.setTen(JSONUtil.toJsonStr(placeOrderDTO.getTen()));
            }
            permutation.setNotes(placeOrderDTO.getNotes());
            permutation.setTimes(placeOrderDTO.getTimes());
            permutation.setType(type);
            permutation.setCreateTime(new Date());
            permutation.setUpdateTime(new Date());
            permutationMapper.insert(permutation);
            ids.add(permutation.getId());
            List<PermutationVO> makeUp = new ArrayList<>();
            //组合成方案
            if (type.equals(LotteryOrderTypeEnum.ARRAY.getKey())) {
                //排列3
                if (placeOrderDTO.getMode().equals("0")) {
                    makeUp = PermutationUtil.makeUp(type, placeOrderDTO.getMode(), permutationAward.getStageNumber() + 1, placeOrderDTO.getTimes(), placeOrderDTO.getHundred().toArray(), placeOrderDTO.getTen().toArray(), placeOrderDTO.getIndividual().toArray());
                } else if (placeOrderDTO.getMode().equals("1") || placeOrderDTO.getMode().equals("2") || placeOrderDTO.getMode().equals("3") || placeOrderDTO.getMode().equals("4")) {
                    makeUp = PermutationUtil.makeUp(type, placeOrderDTO.getMode(), permutationAward.getStageNumber() + 1, placeOrderDTO.getTimes(), placeOrderDTO.getIndividual().toArray());
                } else if (placeOrderDTO.getMode().equals("5")) {
                    makeUp = PermutationUtil.makeUp(type, placeOrderDTO.getMode(), permutationAward.getStageNumber() + 1, placeOrderDTO.getTimes(), placeOrderDTO.getTen().toArray(), placeOrderDTO.getIndividual().toArray());
                }
            } else if (type.equals(LotteryOrderTypeEnum.ARRANGE.getKey())) {
                //排列5
                makeUp = PermutationUtil.makeUp(type, placeOrderDTO.getMode(), permutationAward.getStageNumber() + 1, placeOrderDTO.getTimes(), placeOrderDTO.getMyriad().toArray(), placeOrderDTO.getKilo().toArray(), placeOrderDTO.getHundred().toArray(), placeOrderDTO.getTen().toArray(), placeOrderDTO.getIndividual().toArray());
            } else if (type.equals(LotteryOrderTypeEnum.SEVEN_STAR.getKey())) {
                //七星彩
                makeUp = PermutationUtil.makeUp(type, placeOrderDTO.getMode(), permutationAward.getStageNumber() + 1, placeOrderDTO.getTimes(), placeOrderDTO.getHundredMyriad().toArray(), placeOrderDTO.getTenMyriad().toArray(), placeOrderDTO.getMyriad().toArray(), placeOrderDTO.getKilo().toArray(), placeOrderDTO.getHundred().toArray(), placeOrderDTO.getTen().toArray(), placeOrderDTO.getIndividual().toArray());
            } else if (type.equals(LotteryOrderTypeEnum.GRAND_LOTTO.getKey())) {
                //大乐透
                makeUp = JSONUtil.toList(placeOrderDTO.getSchemeDetails(), PermutationVO.class);
            }
            list.addAll(makeUp);
        }
        // 3.创建订单
        LotteryOrderDO order = new LotteryOrderDO();
        order.setOrderId(OrderNumberGenerationUtil.getOrderId());
        order.setUserId(userId);
        order.setPrice(price);
        order.setTargetIds(StrUtil.join(",", ids));
        order.setType(LotteryOrderTypeEnum.valueOFS(type).getKey());
        order.setSchemeDetails(JSONUtil.toJsonStr(list));
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderMapper.insert(order);
        placeOrder.setId(order.getId());
        return placeOrder;
    }

    @Override
    public CommonListVO<PermutationRecordVO> record(String type) {
        CommonListVO<PermutationRecordVO> commonList = new CommonListVO<>();
        List<PermutationRecordVO> list = new ArrayList<>();
        List<PermutationAwardDO> awardList = permutationAwardMapper.selectList(new QueryWrapper<PermutationAwardDO>().lambda().eq(PermutationAwardDO::getType, type).orderByDesc(PermutationAwardDO::getCreateTime));
        for (PermutationAwardDO permutationAwardDO : awardList) {
            PermutationRecordVO permutationRecord = new PermutationRecordVO();
            BeanUtils.copyProperties(permutationAwardDO, permutationRecord);
            permutationRecord.setRewardList(Arrays.asList(permutationAwardDO.getReward().split(",")));
            list.add(permutationRecord);
        }
        commonList.setVoList(list);
        return commonList;
    }

    @Override
    @TenantIgnore
    public BaseVO calculation(PermutationAwardDO permutationAward) {
        //修改所有这期下注的开奖结果
        PermutationDO permutation = new PermutationDO();
        permutation.setReward(permutationAward.getReward());
        permutationMapper.update(permutation, new QueryWrapper<PermutationDO>().lambda().eq(PermutationDO::getStageNumber, permutationAward.getStageNumber()).eq(PermutationDO::getType, permutationAward.getType()));

        //最后出奖的时候，如果还有未出票的订单进行退回金额
        List<LotteryOrderDO> retreatOrderList = orderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.ARRAY.getKey()).eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_ISSUED.getKey()).eq(LotteryOrderDO::getType, permutationAward.getType()));
        for (LotteryOrderDO lotteryOrderDO : retreatOrderList) {
            List<PermutationDO> permutationList = permutationMapper.selectBatchIds(Convert.toList(Integer.class, lotteryOrderDO.getTargetIds()));
            for (PermutationDO permutationDO : permutationList) {
                if (permutationDO.getStageNumber().equals(permutationAward.getStageNumber())) {
                    UserDO userDO = userMapper.selectById(lotteryOrderDO.getUserId());
                    userDO.setGold(userDO.getGold().add(lotteryOrderDO.getPrice()));
                    userMapper.updateById(userDO);
                    //修改订单状态
                    lotteryOrderDO.setState(LotteryOrderStateEnum.REFUND.getKey());
                    orderMapper.updateById(lotteryOrderDO);
                    //添加钱包记录
                    addRecord(lotteryOrderDO);
                }
            }
        }
        List<LotteryOrderDO> orderList = orderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getType, permutationAward.getType()).eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_AWARDED.getKey()));
        for (LotteryOrderDO lotteryOrderDO : orderList) {
            //根据id查询投注信息
            List<PermutationDO> permutationList = permutationMapper.selectBatchIds(Convert.toList(Integer.class, lotteryOrderDO.getTargetIds()));
            //判断排列3选的是直选还是组三还是组九
            Long price = 0L;
            for (PermutationDO permutationDO : permutationList) {
                if (permutationDO.getStageNumber().equals(permutationAward.getStageNumber())) {
                    Boolean flag = false;
                    Long bonus = 0L;
                    //是排列3还是排列5
                    if (permutationAward.getType().equals(LotteryOrderTypeEnum.ARRAY.getKey())) {
                        //直选
                        if (ObjectUtil.equal(permutationDO.getMode(), "0")) {
                            bonus = PermutationUtil.directlyElected(permutationDO.getHundred().split(","), permutationDO.getTen().split(","), permutationDO.getIndividual().split(","), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                        //组三
                        if (ObjectUtil.equal(permutationDO.getMode(), "1")) {
                            bonus = PermutationUtil.GroupThree(permutationDO.getIndividual(), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                        //组六
                        if (ObjectUtil.equal(permutationDO.getMode(), "2")) {
                            //计算用户买的是否中奖
                            bonus = PermutationUtil.groupSix(permutationDO.getIndividual(), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                        //直选和值
                        if (ObjectUtil.equal(permutationDO.getMode(), "3")) {
                            //计算用户买的是否中奖
                            bonus = PermutationUtil.directlyElectedGentle(permutationDO.getIndividual().split(","), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                        //组选和值
                        if (ObjectUtil.equal(permutationDO.getMode(), "4")) {
                            //计算用户买的是否中奖
                            bonus = PermutationUtil.groupGentle(permutationDO.getIndividual().split(","), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                        //组三复式
                        if (ObjectUtil.equal(permutationDO.getMode(), "5")) {
                            //计算用户买的是否中奖
                            bonus = PermutationUtil.compound(permutationDO.getTen().split(","), permutationDO.getIndividual().split(","), permutationAward.getReward());
                            flag = !bonus.equals(0L) ? true : false;
                        }
                    } else if (permutationAward.getType().equals(LotteryOrderTypeEnum.ARRANGE.getKey())) {
                        bonus = PermutationUtil.arrangeElected(permutationDO.getMyriad().split(","), permutationDO.getKilo().split(","), permutationDO.getHundred().split(","), permutationDO.getTen().split(","), permutationDO.getIndividual().split(","), permutationAward.getReward());
                        flag = !bonus.equals(0L) ? true : false;
                    } else if (permutationAward.getType().equals(LotteryOrderTypeEnum.SEVEN_STAR.getKey())) {
                        //七星彩开奖算法
                        bonus = PermutationUtil.sevenStarLottery(permutationDO.getHundredMyriad().split(","), permutationDO.getTenMyriad().split(","), permutationDO.getMyriad().split(","), permutationDO.getKilo().split(","), permutationDO.getHundred().split(","), permutationDO.getTen().split(","), permutationDO.getIndividual().split(","), permutationAward.getReward(), permutationAward.getMoneyAward());
                        flag = !bonus.equals(0L) ? true : false;
                    } else if (permutationAward.getType().equals(LotteryOrderTypeEnum.GRAND_LOTTO.getKey())) {
                        //大乐透开奖算法
                        bonus = GrandLottoUtil.award(JSONUtil.toList(permutationDO.getTen(), GrandLottoObjDTO.class), JSONUtil.toList(permutationDO.getIndividual(), GrandLottoObjDTO.class), permutationAward.getReward(), permutationAward.getMoneyAward().split(",")[0], permutationAward.getMoneyAward().split(",")[1]);
                        flag = !bonus.equals(0L) ? true : false;
                    }
                    if (flag) {
                        price += bonus * permutationDO.getTimes();
                    }
                }
            }
            if (price != 0) {
                //中奖就修改订单状态为待派奖
                lotteryOrderDO.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                lotteryOrderDO.setUpdateTime(new Date());
                //设置中奖金额
                lotteryOrderDO.setWinPrice(new BigDecimal(price));
                orderMapper.updateById(lotteryOrderDO);
            } else {
                //没有状态改为未中奖
                lotteryOrderDO.setState(LotteryOrderStateEnum.FAIL_TO_WIN.getKey());
                lotteryOrderDO.setUpdateTime(new Date());
                orderMapper.updateById(lotteryOrderDO);
            }
        }
        return new BaseVO();
    }

    private void addRecord(LotteryOrderDO lotteryOrder) {
        PayOrderDO payOrder = new PayOrderDO();
        payOrder.setOrderId(OrderNumberGenerationUtil.getOrderId());
        String type = "";
        if (StrUtil.equals(lotteryOrder.getType(), LotteryOrderTypeEnum.ARRAY.getKey())) {
            type = PayOrderTypeEnum.ARRAY_REFUND.getKey();
        } else if (StrUtil.equals(lotteryOrder.getType(), LotteryOrderTypeEnum.ARRANGE.getKey())) {
            type = PayOrderTypeEnum.ARRANGE_REFUND.getKey();
        } else if (StrUtil.equals(lotteryOrder.getType(), LotteryOrderTypeEnum.SEVEN_STAR.getKey())) {
            type = PayOrderTypeEnum.SEVEN_STAR_REFUND.getKey();
        }
        payOrder.setType(type);
        payOrder.setState(PayOrderStateEnum.PAYMENT.getKey());
        payOrder.setCreateTime(new Date());
        payOrder.setTenantId(lotteryOrder.getTenantId());
        payOrder.setUpdateTime(new Date());
        payOrder.setPayType(PayTypeEnum.APP.getKey());
        payOrder.setUserId(lotteryOrder.getUserId());
        payOrder.setPrice(lotteryOrder.getPrice());
        payOrderMapper.insert(payOrder);
    }
}
