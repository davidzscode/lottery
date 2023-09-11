package com.qihang.schedul;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.domain.basketball.BasketballMatchDO;
import com.qihang.domain.beidan.BeiDanMatchDO;
import com.qihang.domain.football.FootballMatchDO;
import com.qihang.domain.winburden.WinBurdenMatchDO;
import com.qihang.enumeration.ball.BettingStateEnum;
import com.qihang.mapper.basketball.BasketballMatchMapper;
import com.qihang.mapper.beidan.BeiDanMatchMapper;
import com.qihang.mapper.football.FootballMatchMapper;
import com.qihang.mapper.winburden.WinBurdenMatchMapper;
import com.qihang.reptile.LotteryProcessor;
import com.qihang.service.basketball.IBasketballMatchService;
import com.qihang.service.beidan.IBeiDanMatchService;
import com.qihang.service.football.IFootballMatchService;
import com.qihang.service.order.ILotteryOrderService;
import com.qihang.service.winburden.IWinBurdenMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qihang
 */
@Configuration
@EnableScheduling
@Lazy(value = false)
@Slf4j
public class StaticScheduleTask {
    @Resource
    private LotteryProcessor lotteryProcessor;

    @Resource
    private FootballMatchMapper footballMatchMapper;

    @Resource
    private BasketballMatchMapper basketballMatchMapper;

    @Resource
    private IFootballMatchService footballMatchService;

    @Resource
    private IBasketballMatchService basketballMatchService;

    @Resource
    private BeiDanMatchMapper beiDanMatchMapper;
    @Resource
    private IBeiDanMatchService beiDanMatchService;

    @Resource
    private ILotteryOrderService lotteryOrderService;

    @Resource
    private WinBurdenMatchMapper winBurdenMatchMapper;

    @Resource
    private IWinBurdenMatchService winBurdenMatchService;

    /**
     * 处理订单没出票采用邮件通知
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    private void toEmail() {
        lotteryOrderService.NoTicketIssuedSedEmail();
    }


    @Scheduled(cron = "0 0/9 * * * ?")
    private void configureProcessorTasks() {
       try {
           log.info("执行爬虫run【{}】", DateUtil.now());
           //执行爬虫
           lotteryProcessor.run();
       }catch (Exception exception){
           log.info("执行爬虫【{}】错误:"+exception.getMessage(), DateUtil.now());
       }
    }

    /**
     * 执行每60秒执行派奖计算
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    private void configureTasks() {
        log.info("爬虫定时任务触发时间【{}】", DateUtil.now());
        //查询足球当天的比赛记录
        List<FootballMatchDO> footballMatchList = footballMatchMapper.selectList(new QueryWrapper<FootballMatchDO>().lambda().le(FootballMatchDO::getDeadline, DateUtil.now()).eq(FootballMatchDO::getState, "1"));
        //循环查找看比赛截止时间是否到了，到了就关闭这个比赛下注
        Date date = new Date();
        for (FootballMatchDO footballMatch : footballMatchList) {
            if (date.compareTo(footballMatch.getDeadline()) > 0 || date.compareTo(footballMatch.getDeadline()) == 0) {
                footballMatch.setState(BettingStateEnum.NO.getKey());
                footballMatchMapper.updateById(footballMatch);
            }
        }
        //篮球当前的记录
        List<BasketballMatchDO> basketballMatchList = basketballMatchMapper.selectList(new QueryWrapper<BasketballMatchDO>().lambda().le(BasketballMatchDO::getDeadline, DateUtil.now()).eq(BasketballMatchDO::getState, "1"));
        //循环查找看比赛截止时间是否到了，到了就关闭这个比赛下注
        for (BasketballMatchDO basketballMatch : basketballMatchList) {
            if (date.compareTo(basketballMatch.getDeadline()) > 0 || date.compareTo(basketballMatch.getDeadline()) == 0) {
                basketballMatch.setState(BettingStateEnum.NO.getKey());
                basketballMatchMapper.updateById(basketballMatch);
            }
        }

        //北单当天的记录
        List<BeiDanMatchDO> beiDanMatchList = beiDanMatchMapper.selectList(new QueryWrapper<BeiDanMatchDO>().lambda().le(BeiDanMatchDO::getDeadline, DateUtil.now()).eq(BeiDanMatchDO::getState, "1"));
        //循环查找看比赛截止时间是否到了，到了就关闭这个比赛下注
        for (BeiDanMatchDO beiDanMatch : beiDanMatchList) {
            if (date.compareTo(beiDanMatch.getDeadline()) > 0 || date.compareTo(beiDanMatch.getDeadline()) == 0) {
                beiDanMatch.setState(BettingStateEnum.NO.getKey());
                beiDanMatchMapper.updateById(beiDanMatch);
            }
        }
        //胜负彩
        List<WinBurdenMatchDO> winBurdenMatchList = winBurdenMatchMapper.selectList(new QueryWrapper<WinBurdenMatchDO>().lambda().le(WinBurdenMatchDO::getDeadline, DateUtil.now()).eq(WinBurdenMatchDO::getState, "1"));
        //循环查找看比赛截止时间是否到了，到了就关闭这个比赛下注
        for (WinBurdenMatchDO winBurdenMatch : winBurdenMatchList) {
            if (date.compareTo(winBurdenMatch.getDeadline()) > 0 || date.compareTo(winBurdenMatch.getDeadline()) == 0) {
                winBurdenMatch.setState(BettingStateEnum.NO.getKey());
                winBurdenMatchMapper.updateById(winBurdenMatch);
            }
        }


        //足球出奖结果计算
        footballMatchService.award();

        //篮球出奖结果计算
        basketballMatchService.award();

        //北单出奖结果计算
        beiDanMatchService.award();

        //14场胜负开奖
        winBurdenMatchService.victoryDefeatAward();

        //任九开奖
        winBurdenMatchService.renJiuAward();
    }
}
