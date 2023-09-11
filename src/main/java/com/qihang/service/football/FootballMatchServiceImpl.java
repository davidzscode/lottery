package com.qihang.service.football;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.order.OrderNumberGenerationUtil;
import com.qihang.common.util.reward.FootballUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.football.dto.FootballMatchDTO;
import com.qihang.controller.football.vo.FootballMatchVO;
import com.qihang.controller.football.vo.FootballVO;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.domain.documentary.DocumentaryDO;
import com.qihang.domain.documentary.DocumentaryUserDO;
import com.qihang.domain.football.FootballMatchDO;
import com.qihang.domain.order.LotteryOrderDO;
import com.qihang.domain.order.PayOrderDO;
import com.qihang.domain.racingball.RacingBallDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.ball.BettingStateEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderStateEnum;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayOrderStateEnum;
import com.qihang.enumeration.order.pay.PayOrderTypeEnum;
import com.qihang.enumeration.order.pay.PayTypeEnum;
import com.qihang.mapper.documentary.DocumentaryMapper;
import com.qihang.mapper.documentary.DocumentaryUserMapper;
import com.qihang.mapper.football.FootballMatchMapper;
import com.qihang.mapper.order.LotteryOrderMapper;
import com.qihang.mapper.order.PayOrderMapper;
import com.qihang.mapper.racingball.RacingBallMapper;
import com.qihang.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 足彩 胜负平表 服务实现类
 *
 * @author qihang
 * @since 2022-10-05
 */
@Service
@Slf4j
public class FootballMatchServiceImpl extends ServiceImpl<FootballMatchMapper, FootballMatchDO> implements IFootballMatchService {

    @Resource
    private FootballMatchMapper footballMatchMapper;

    @Resource
    private LotteryOrderMapper lotteryOrderMapper;

    @Resource
    private RacingBallMapper racingBallMapper;

    @Resource
    private PayOrderMapper payOrderMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private DocumentaryMapper documentaryMapper;
    @Resource
    private DocumentaryUserMapper documentaryUserMapper;


    @Override
    public CommonListVO<FootballVO> footballMatchList() {
        CommonListVO<FootballVO> commonList = new CommonListVO<>();
        List<FootballVO> footballList = new ArrayList<>();
        List<FootballMatchDO> footballWinEvenLoseDist = footballMatchMapper.selectList(new QueryWrapper<FootballMatchDO>().lambda().eq(FootballMatchDO::getState, BettingStateEnum.YES.getKey()));
        Map<String, List<FootballMatchDO>> map = footballWinEvenLoseDist.stream().collect(Collectors.groupingBy(FootballMatchDO::getStartTime));
        //对map的key进行排序
        map = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Integer id = 0;
        for (Map.Entry<String, List<FootballMatchDO>> entry : map.entrySet()) {
            FootballVO football = new FootballVO();
            football.setStartTime(entry.getKey());
            football.setCount(entry.getValue().size());
            List<FootballMatchVO> footballMatchList = new ArrayList<>();
            //排序
            List<FootballMatchDO> list = entry.getValue();
            list.sort(Comparator.comparing(FootballMatchDO::getNumber));
            for (FootballMatchDO footballMatch : list) {
                FootballMatchVO footballMatchVO = new FootballMatchVO();
                BeanUtils.copyProperties(footballMatch, footballMatchVO);
                //默认选择项为0，方便前端好控制选择了几项
                footballMatchVO.setChoiceCount(0);
                List<Map<String, Object>> mapList = new ArrayList<>();
                Map<String, Object> mapObj = null;

                /*============================让球组成list<map>结构==============================*/
                if (StrUtil.isNotBlank(footballMatch.getLetOdds())) {
                    String[] letArr = footballMatch.getLetOdds().split(",");
                    for (int i = 0; i < letArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("odds", letArr[i]);
                        mapObj.put("active", false);
                        if (i == 0) {
                            mapObj.put("describe", "胜");
                        } else if (i == 1) {
                            mapObj.put("describe", "平");
                        } else if (i == 2) {
                            mapObj.put("describe", "负");
                        }
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                footballMatchVO.setLetOddsList(mapList);

                /*============================不让球组成list<map>===============================*/

                //清空原来的数据
                mapList = new ArrayList<>();
                if (StrUtil.isNotBlank(footballMatch.getNotLetOdds())) {
                    String[] notLetArr = footballMatch.getNotLetOdds().split(",");
                    for (int i = 0; i < notLetArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("odds", notLetArr[i]);
                        mapObj.put("active", false);
                        if (i == 0) {
                            mapObj.put("describe", "胜");
                        } else if (i == 1) {
                            mapObj.put("describe", "平");
                        } else if (i == 2) {
                            mapObj.put("describe", "负");
                        }
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                footballMatchVO.setNotLetOddsList(mapList);

                /*==============================总进球组成list<map>============================*/

                //清空原来的数据
                mapList = new ArrayList<>();
                if (StrUtil.isNotBlank(footballMatch.getGoalOdds())) {
                    String[] goalItems = {"0", "1", "2", "3", "4", "5", "6", "7+"};
                    String[] goalArr = footballMatch.getGoalOdds().split(",");
                    for (int i = 0; i < goalArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("describe", goalItems[i]);
                        mapObj.put("odds", goalArr[i]);
                        mapObj.put("active", false);
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                footballMatchVO.setGoalOddsList(mapList);


                /*==============================总比分组成list<map>============================*/

                //清空原来的数据
                mapList = new ArrayList<>();
                if (StrUtil.isNotBlank(footballMatch.getScoreOdds())) {
                    String[] scoreArr = footballMatch.getScoreOdds().split(",");
                    String[] scoreItems = {"1:0", "2:0", "2:1", "3:0", "3:1", "3:2", "4:0", "4:1", "4:2", "5:0", "5:1", "5:2", "胜其他", "0:0", "1:1", "2:2", "3:3", "平其他", "0:1", "0:2", "1:2", "0:3", "1:3", "2:3", "0:4", "1:4", "2:4", "0:5", "1:5", "2:5", "负其他"};
                    for (int i = 0; i < scoreArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("describe", scoreItems[i]);
                        mapObj.put("odds", scoreArr[i]);
                        mapObj.put("active", false);
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                footballMatchVO.setScoreOddsList(mapList);


                /*==============================半全场组成list<map>============================*/

                //清空原来的数据
                mapList = new ArrayList<>();
                if (StrUtil.isNotBlank(footballMatch.getHalfWholeOdds())) {
                    String[] halfWholeItems = {"胜-胜", "胜-平", "胜-负", "平-胜", "平-平", "平-负", "负-胜", "负-平", "负-负"};
                    String[] halfWholeOddsArr = footballMatch.getHalfWholeOdds().split(",");
                    for (int i = 0; i < halfWholeOddsArr.length; i++) {
                        mapObj = new HashMap<>(3);
                        mapObj.put("id", ++id);
                        mapObj.put("describe", halfWholeItems[i]);
                        mapObj.put("odds", halfWholeOddsArr[i]);
                        mapObj.put("active", false);
                        mapObj.put("index", i);
                        mapList.add(mapObj);
                    }
                }
                //添加对对象中
                footballMatchVO.setHalfWholeOddsList(mapList);

                footballMatchList.add(footballMatchVO);
            }
            football.setFootballMatchList(footballMatchList);
            footballList.add(football);
        }
        commonList.setVoList(footballList);
        return commonList;
    }

    @Override
    public BallCalculationVO calculation(BallCalculationDTO ballCalculation) {
        return FootballUtil.calculation(ballCalculation.getFootballMatchList(), ballCalculation.getMultiple(), ballCalculation.getPssTypeList());
    }

    @Override
    @TenantIgnore
    public BaseVO award() {
        //查询未出票的订单
        List<LotteryOrderDO> orderNotList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_ISSUED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.FOOTBALL.getKey()));
        for (LotteryOrderDO lotteryOrderDO : orderNotList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, lotteryOrderDO.getTargetIds()));
            Boolean flag = true;
            for (RacingBallDO racingBallDO : racingBallList) {
                FootballMatchDO footballMatch = footballMatchMapper.selectById(racingBallDO.getTargetId());
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(footballMatch.getAward())) {
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
        List<LotteryOrderDO> orderList = lotteryOrderMapper.selectList(new QueryWrapper<LotteryOrderDO>().lambda().eq(LotteryOrderDO::getState, LotteryOrderStateEnum.TO_BE_AWARDED.getKey()).eq(LotteryOrderDO::getType, LotteryOrderTypeEnum.FOOTBALL.getKey()));
        for (LotteryOrderDO order : orderList) {
            //查询下注的列表
            List<RacingBallDO> racingBallList = racingBallMapper.selectBatchIds(Convert.toList(Integer.class, order.getTargetIds()));
            //用戶下注列表
            List<FootballMatchDTO> footballMatchList = new ArrayList<>();
            //每场比赛出奖比赛列表
            List<String> list = new ArrayList<>();
            Boolean flag = true;
            for (RacingBallDO racingBallDO : racingBallList) {
                //下注結果組成list
                footballMatchList.add(JSONUtil.toBean(racingBallDO.getContent(), FootballMatchDTO.class));
                //查询下注对应的比赛赛果
                FootballMatchDO footballMatch = footballMatchMapper.selectById(racingBallDO.getTargetId());
                //如果比赛还没有出结果直接跳出
                if (StrUtil.isBlank(footballMatch.getAward())) {
                    flag = false;
                    break;
                }
                list.add(footballMatch.getAward());
            }
            if (flag) {
                //过关类型
                List<Integer> pssTypeList = Convert.toList(Integer.class, racingBallList.get(0).getType());
                //倍数
                Integer multiple = racingBallList.get(0).getTimes();
                //计算用户有没有中奖，中奖了把每一注的金额进行累加在返回
                Double price = FootballUtil.award(footballMatchList, multiple, pssTypeList, list);
                //等于0相当于没有中奖
                if (price == 0) {
                    order.setState(LotteryOrderStateEnum.FAIL_TO_WIN.getKey());
                } else {
                    //已经中奖
                    //查询订单是不是发单订单
                    DocumentaryDO documentary = documentaryMapper.selectOne(new QueryWrapper<DocumentaryDO>().lambda().eq(DocumentaryDO::getLotteryOrderId, order.getId()));
                    //查询是否是跟单
                    DocumentaryUserDO documentaryUser = documentaryUserMapper.selectOne(new QueryWrapper<DocumentaryUserDO>().lambda().eq(DocumentaryUserDO::getLotteryOrderId, order.getId()));

                    if (ObjectUtil.isNotNull(documentary)) {
                        //是发单订单
                        order.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                        BigDecimal winPrice = NumberUtil.round(price, 2);
                        order.setWinPrice(winPrice);
                    } else if (ObjectUtil.isNotNull(documentaryUser)) {
                        //是跟单订单
                        //查询跟单是那个用户的订单
                        documentary = documentaryMapper.selectOne(new QueryWrapper<DocumentaryDO>().lambda().eq(DocumentaryDO::getId, documentaryUser.getDocumentaryId()));
                        BigDecimal winPrice = NumberUtil.round(price, 2);
                        //需要扣除比赛后的金额给发单用户，根据发单的设置的佣金比例来计算
                        BigDecimal proportionPrice = winPrice.multiply(new BigDecimal((float) documentary.getCommission() / 100)).setScale(2, RoundingMode.HALF_UP);
                        order.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                        order.setWinPrice(winPrice.subtract(proportionPrice));
                        //给发单用户加金额
                        UserDO userDO = userMapper.selectById(documentary.getUserId());
                        userDO.setGold(userDO.getGold().add(proportionPrice));
                        userMapper.updateById(userDO);
                        //添加流水记录
                        PayOrderDO payOrder = new PayOrderDO();
                        payOrder.setOrderId(OrderNumberGenerationUtil.getOrderId());
                        payOrder.setType(PayOrderTypeEnum.ISSUING_REWARD.getKey());
                        payOrder.setState(PayOrderStateEnum.PAYMENT.getKey());
                        payOrder.setCreateTime(new Date());
                        payOrder.setTenantId(order.getTenantId());
                        payOrder.setUpdateTime(new Date());
                        payOrder.setPayType(PayTypeEnum.APP.getKey());
                        payOrder.setUserId(documentary.getUserId());
                        payOrder.setPrice(proportionPrice);
                        payOrderMapper.insert(payOrder);
                    } else {
                        order.setState(LotteryOrderStateEnum.WAITING_AWARD.getKey());
                        order.setWinPrice(NumberUtil.round(price, 2));
                    }
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
        payOrder.setType(PayOrderTypeEnum.FOOTBALL_REFUND.getKey());
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
