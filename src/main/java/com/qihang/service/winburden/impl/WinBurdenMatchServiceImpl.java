package com.qihang.service.winburden.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.order.OrderNumberGenerationUtil;
import com.qihang.common.util.reward.RenJiuUtil;
import com.qihang.common.util.reward.WinBurdenUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.winburden.dto.WinBurdenMatchDTO;
import com.qihang.controller.winburden.vo.WinBurdenMatchVO;
import com.qihang.controller.winburden.vo.WinBurdenVO;
import com.qihang.domain.order.LotteryOrderDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.racingball.RacingBallDO;
import com.qihang.domain.user.UserDO;
import com.qihang.domain.winburden.WinBurdenMatchDO;
import com.qihang.enumeration.ball.BettingStateEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderStateEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayTypeEnum;
import com.qihang.mapper.order.LotteryOrderMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.racingball.RacingBallMapper;
import com.qihang.mapper.user.UserMapper;
import com.qihang.mapper.winburden.WinBurdenMatchMapper;
import com.qihang.service.winburden.IWinBurdenMatchService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qihang
 * @since 2023-03-30
 */
@Service
public class WinBurdenMatchServiceImpl extends ServiceImpl<WinBurdenMatchMapper, WinBurdenMatchDO> implements IWinBurdenMatchService {

    @Resource
    private WinBurdenMatchMapper winBurdenMatchMapper;


    @Resource
    private LotteryOrderMapper lotteryOrderMapper;

    @Resource
    private RacingBallMapper racingBallMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PayOrderMapper payOrderMapper;

    @Override
    public CommonListVO<WinBurdenVO> winBurdenMatchList() {
        CommonListVO<WinBurdenVO> commonList = new CommonListVO<>();
        List<WinBurdenVO> winBurdenList = new ArrayList<>();
        List<WinBurdenMatchDO> winBurdenMatchList = winBurdenMatchMapper.selectList(new QueryWrapper<WinBurdenMatchDO>().lambda().eq(WinBurdenMatchDO::getState, BettingStateEnum.YES.getKey()));
        Map<String, List<WinBurdenMatchDO>> map = winBurdenMatchList.stream().collect(Collectors.groupingBy(WinBurdenMatchDO::getIssueNo));
        Integer id = 0;
        for (Map.Entry<String, List<WinBurdenMatchDO>> entry : map.entrySet()) {
            WinBurdenVO winBurden = new WinBurdenVO();
            winBurden.setIssueNo(entry.getKey());

            List<WinBurdenMatchVO> winBurdenMatchVOList = new ArrayList<>();
            //排序
            List<WinBurdenMatchDO> list = entry.getValue();
            list = list.stream().sorted(Comparator.comparing(data -> Integer.valueOf(data.getNumber()))).collect(Collectors.toList());
            for (WinBurdenMatchDO winBurdenMatch : list) {
                WinBurdenMatchVO winBurdenMatchVO = new WinBurdenMatchVO();
                BeanUtils.copyProperties(winBurdenMatch, winBurdenMatchVO);
                //默认选择项为0，方便前端好控制选择了几项
                winBurdenMatchVO.setChoiceCount(0);
                winBurdenMatchVO.setIsGallbladder(false);
                List<Map<String, Object>> mapList = new ArrayList<>();
                Map<String, Object> mapObj = null;

                /*============================胜平负组成list<map>结构==============================*/
                if (StrUtil.isNotBlank(winBurdenMatch.getNotLetOdds())) {
                    String[] notLetArr = winBurdenMatch.getNotLetOdds().split(",");
                    for (int i = 0; i < notLetArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("odds", notLetArr[i]);
                        mapObj.put("active", false);
                        if (i == 0) {
                            mapObj.put("describe", "胜");
                            mapObj.put("desc", "3");
                        } else if (i == 1) {
                            mapObj.put("describe", "平");
                            mapObj.put("desc", "1");
                        } else if (i == 2) {
                            mapObj.put("describe", "负");
                            mapObj.put("desc", "0");
                        }
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                winBurdenMatchVO.setNotLetOddsList(mapList);

                winBurdenMatchVOList.add(winBurdenMatchVO);
            }
            winBurden.setWinBurdenMatchList(winBurdenMatchVOList);
            winBurdenList.add(winBurden);
        }
        commonList.setVoList(winBurdenList);
        return commonList;
    }

    @Override
    public BallCalculationVO calculation(BallCalculationDTO ballCalculation) {
        if (ObjectUtil.equal(ballCalculation.getType(), LotteryOrderTypeEnum.VICTORY_DEFEAT.getKey())) {
            return WinBurdenUtil.calculation(ballCalculation);
        } else {
            return RenJiuUtil.calculation(ballCalculation);
        }
    }

    @Override
    @TenantIgnore
    public BaseVO victoryDefeatAward() {
        //查询未出票的订单
        List<LotteryOrderDO> orderNotList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_ISSUED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.VICTORY_DEFEAT.getKey()));
        for (LotteryOrderDO lotteryOrderDO : orderNotList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, lotteryOrderDO.getTargetIds()));
            Boolean flag = true;
            for (RacingBallDO racingBallDO : racingBallList) {
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchMapper.selectById(racingBallDO.getTargetId());
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(winBurdenMatch.getAward())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //如果订单为出票并且结果也出了情况下进行退票操作
                UserDO userDO = userMapper.selectById(lotteryOrderDO.getUserId());
                userDO.setGold(userDO.getGold().add(lotteryOrderDO.getPrice()));
                userMapper.updateById(userDO);
                //修改订单状态
                lotteryOrderDO.setState(LotteryOrderStateEnum.REFUND.getKey());
                lotteryOrderMapper.updateById(lotteryOrderDO);
                //添加钱包记录
                addRecord(lotteryOrderDO);
            }
        }
        //查询足球已经下注的订单列表
        List<LotteryOrderDO> orderList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_AWARDED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.VICTORY_DEFEAT.getKey()));
        for (LotteryOrderDO order : orderList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, order.getTargetIds()));
            //用戶下注列表
            List<WinBurdenMatchDTO> winBurdenMatchList = new ArrayList<>();
            //每场比赛出奖比赛列表
            List<String> list = new ArrayList<>();
            Boolean flag = true;
            String moneyAward = "";
            for (RacingBallDO racingBallDO : racingBallList) {
                //下注結果組成list
                winBurdenMatchList.add(JSONUtil.toBean(racingBallDO.getContent(), WinBurdenMatchDTO.class));
                //查询下注对应的比赛赛果
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchMapper.selectById(racingBallDO.getTargetId());
                moneyAward = winBurdenMatch.getMoneyAward();
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(winBurdenMatch.getAward()) || StrUtil.isBlank(winBurdenMatch.getMoneyAward())) {
                    flag = false;
                    break;
                }
                list.add(winBurdenMatch.getAward());
            }
            if (flag) {
                //倍数
                Integer multiple = racingBallList.get(0).getTimes();
                //1 2 等奖每注奖金
                List<Double> moneyAwardList = Convert.toList(Double.class, moneyAward);
                //计算用户有没有中奖，中奖了把每一注的金额进行累加在返回
                Double price = WinBurdenUtil.award(winBurdenMatchList, multiple, list, moneyAwardList.get(0), moneyAwardList.get(1));
                //等于0相当于没有中奖
                if (price == 0) {
                    order.setState(LotteryOrderStateEnum.FAIL_TO_WIN.getKey());
                } else {
                    //已经中奖
                    order.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                    order.setWinPrice(NumberUtil.round(price, 2));
                }
                order.setUpdateTime(new Date());
                lotteryOrderMapper.updateById(order);
            }
        }
        return new BaseVO();
    }

    @Override
    @TenantIgnore
    public BaseVO renJiuAward() {
        //查询未出票的订单
        List<LotteryOrderDO> orderNotList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_ISSUED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.REN_JIU.getKey()));
        for (LotteryOrderDO lotteryOrderDO : orderNotList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, lotteryOrderDO.getTargetIds()));
            Boolean flag = true;
            for (RacingBallDO racingBallDO : racingBallList) {
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchMapper.selectById(racingBallDO.getTargetId());
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(winBurdenMatch.getAward())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //如果订单为出票并且结果也出了情况下进行退票操作
                UserDO userDO = userMapper.selectById(lotteryOrderDO.getUserId());
                userDO.setGold(userDO.getGold().add(lotteryOrderDO.getPrice()));
                userMapper.updateById(userDO);
                //修改订单状态
                lotteryOrderDO.setState(LotteryOrderStateEnum.REFUND.getKey());
                lotteryOrderMapper.updateById(lotteryOrderDO);
                //添加钱包记录
                addRecord(lotteryOrderDO);
            }
        }
        //查询足球已经下注的订单列表
        List<LotteryOrderDO> orderList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_AWARDED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.REN_JIU.getKey()));
        for (LotteryOrderDO order : orderList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, order.getTargetIds()));
            //用戶下注列表
            List<WinBurdenMatchDTO> winBurdenMatchList = new ArrayList<>();
            //每场比赛出奖比赛列表
            List<String> list = new ArrayList<>();
            Boolean flag = true;
            String moneyAward = "";
            //期号
            String issueNo = "";
            for (RacingBallDO racingBallDO : racingBallList) {
                //下注結果組成list
                winBurdenMatchList.add(JSONUtil.toBean(racingBallDO.getContent(), WinBurdenMatchDTO.class));
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchMapper.selectById(racingBallDO.getTargetId());
                issueNo = winBurdenMatch.getIssueNo();
            }

            //根据期号查询14场比赛赛果
            List<WinBurdenMatchDO> winBurdenMatchDOList = winBurdenMatchMapper.selectList(new QueryWrapper<WinBurdenMatchDO>().lambda().eq(WinBurdenMatchDO::getIssueNo, issueNo));
            for (WinBurdenMatchDO winBurdenMatchDO : winBurdenMatchDOList) {
                moneyAward = winBurdenMatchDO.getMoneyAward();
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(winBurdenMatchDO.getAward()) || StrUtil.isBlank(winBurdenMatchDO.getMoneyAward())) {
                    flag = false;
                    break;
                }
                list.add(winBurdenMatchDO.getAward());
            }
            if (flag) {
                //倍数
                Integer multiple = racingBallList.get(0).getTimes();
                //1 2 等奖每注奖金
                List<Double> moneyAwardList = Convert.toList(Double.class, moneyAward);
                //计算用户有没有中奖，中奖了把每一注的金额进行累加在返回
                Double price = RenJiuUtil.award(winBurdenMatchList, multiple, list, moneyAwardList.get(2));
                //等于0相当于没有中奖
                if (price == 0) {
                    order.setState(LotteryOrderStateEnum.FAIL_TO_WIN.getKey());
                } else {
                    //已经中奖
                    order.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                    order.setWinPrice(NumberUtil.round(price, 2));
                }
                order.setUpdateTime(new Date());
                lotteryOrderMapper.updateById(order);
            }
        }
        return new BaseVO();
    }

    private void addRecord(LotteryOrderDO lotteryOrder) {
        PayOrderDO payOrder = new PayOrderDO();
        payOrder.setOrderId(OrderNumberGenerationUtil.getOrderId());
        payOrder.setType(PayOrderTypeEnum.VICTORY_DEFEAT_REFUND.getKey());
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
