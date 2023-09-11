package com.qihang.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.constant.CrawlingAddressConstant;
import com.qihang.domain.basketball.BasketballMatchDO;
import com.qihang.domain.beidan.BeiDanMatchDO;
import com.qihang.domain.football.FootballMatchDO;
import com.qihang.domain.omit.OmitDO;
import com.qihang.domain.permutation.PermutationAwardDO;
import com.qihang.domain.winburden.WinBurdenMatchDO;
import com.qihang.enumeration.ball.BettingStateEnum;
import com.qihang.service.basketball.IBasketballMatchService;
import com.qihang.service.beidan.IBeiDanMatchService;
import com.qihang.service.football.IFootballMatchService;
import com.qihang.service.omit.IOmitService;
import com.qihang.service.permutation.IPermutationAwardService;
import com.qihang.service.permutation.IPermutationService;
import com.qihang.service.winburden.IWinBurdenMatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: qihang
 * @description: 体彩算法工具类
 * @time: 2022-10-03 13:33
 */
@Component
public class LotteryPipeline implements Pipeline {

    @Resource
    private IFootballMatchService footballMatchService;

    @Resource
    private IBasketballMatchService basketballMatchService;


    @Resource
    private IPermutationAwardService permutationAwardService;


    @Resource
    private IPermutationService permutationService;

    @Resource
    private IBeiDanMatchService beiDanMatchService;

    @Resource
    private IWinBurdenMatchService winBurdenMatchService;

    @Resource
    private IOmitService omitService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        if (ObjectUtil.equal(url, CrawlingAddressConstant.URL1)) {
            //存储爬取到的足球的比赛数据
            List<FootballMatchDO> footballMatchList = resultItems.get("footballGoalList");
            for (FootballMatchDO footballMatchDO : footballMatchList) {
                FootballMatchDO footballMatch = footballMatchService.getOne(new QueryWrapper<FootballMatchDO>().lambda()
                        .eq(FootballMatchDO::getNumber, footballMatchDO.getNumber())
                        .eq(FootballMatchDO::getMatch, footballMatchDO.getMatch())
                        .eq(FootballMatchDO::getStartTime, footballMatchDO.getStartTime())
                        .eq(FootballMatchDO::getOpenTime, footballMatchDO.getOpenTime()));
                if (ObjectUtil.isNotNull(footballMatch)) {
                    footballMatchDO.setId(footballMatch.getId());
                    footballMatchDO.setUpdateTime(new Date());
                }
            }
            footballMatchService.saveOrUpdateBatch(footballMatchList);
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL2) || ObjectUtil.equal(url, CrawlingAddressConstant.URL16) || ObjectUtil.equal(url, CrawlingAddressConstant.URL17) || ObjectUtil.equal(url, CrawlingAddressConstant.URL20)) {
            //存储爬取到的排列开奖结果

            PermutationAwardDO permutationAward = resultItems.get("permutation");
            if (StrUtil.isNotBlank(permutationAward.getReward())) {
                permutationAward.setCreateTime(new Date());
                permutationAward.setUpdateTime(new Date());
                PermutationAwardDO permutationAwardDO = permutationAwardService.getOne(new QueryWrapper<PermutationAwardDO>().lambda().eq(PermutationAwardDO::getStageNumber, permutationAward.getStageNumber()).eq(PermutationAwardDO::getType, permutationAward.getType()));
                if (ObjectUtil.isNotNull(permutationAwardDO)) {
                    return;
                }
                permutationAwardService.save(permutationAward);
                //计算用户有没有中奖
                permutationService.calculation(permutationAward);
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL3)) {
            List<FootballMatchDO> footballMatchList = resultItems.get("footballGoalList");
            for (FootballMatchDO footballMatchDO : footballMatchList) {
                FootballMatchDO footballMatch = footballMatchService.getOne(new QueryWrapper<FootballMatchDO>().lambda()
                        .eq(FootballMatchDO::getNumber, footballMatchDO.getNumber())
                        .eq(FootballMatchDO::getState, BettingStateEnum.YES.getKey()));
                if (ObjectUtil.isNotNull(footballMatch) && StrUtil.isBlank(footballMatch.getAnalysis())) {
                    footballMatch.setAnalysis(footballMatchDO.getAnalysis());
                    footballMatch.setUpdateTime(new Date());
                    footballMatchService.updateById(footballMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL4)) {
            //存储爬取到的篮球的比赛数据
            List<BasketballMatchDO> basketballMatchList = resultItems.get("basketballMatchList");
            for (BasketballMatchDO basketballMatchDO : basketballMatchList) {
                BasketballMatchDO basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                        .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                        .eq(BasketballMatchDO::getMatch, basketballMatchDO.getMatch())
                        .eq(BasketballMatchDO::getStartTime, basketballMatchDO.getStartTime())
                        .eq(BasketballMatchDO::getOpenTime, basketballMatchDO.getOpenTime()));
                if (ObjectUtil.isNotNull(basketballMatch)) {
                    basketballMatchDO.setId(basketballMatch.getId());
                    basketballMatchDO.setUpdateTime(new Date());
                }
            }
            basketballMatchService.saveOrUpdateBatch(basketballMatchList);
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL5)) {
            List<BasketballMatchDO> basketballMatchList = resultItems.get("basketballMatchList");
            for (BasketballMatchDO basketballMatchDO : basketballMatchList) {
                BasketballMatchDO basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                        .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                        .eq(BasketballMatchDO::getState, BettingStateEnum.YES.getKey()));
                if (ObjectUtil.isNotNull(basketballMatch) && StrUtil.isBlank(basketballMatch.getAnalysis())) {
                    basketballMatch.setAnalysis(basketballMatchDO.getAnalysis());
                    basketballMatch.setUpdateTime(new Date());
                    basketballMatchService.updateById(basketballMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL6)) {
            //足球开奖
            List<FootballMatchDO> footballMatchList = resultItems.get("footballGoalList");
            for (FootballMatchDO footballMatchDO : footballMatchList) {
                FootballMatchDO footballMatch = footballMatchService.getOne(new QueryWrapper<FootballMatchDO>().lambda()
                        .eq(FootballMatchDO::getNumber, footballMatchDO.getNumber())
                        .eq(FootballMatchDO::getOpenTime, footballMatchDO.getOpenTime()));
                if (ObjectUtil.isNotNull(footballMatch)) {
                    footballMatch.setAward(footballMatchDO.getAward());
                    footballMatch.setHalfFullCourt(footballMatchDO.getHalfFullCourt());
                    footballMatch.setUpdateTime(new Date());
                    footballMatchService.updateById(footballMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL7)) {
            //篮球开奖
            List<BasketballMatchDO> basketballMatchList = resultItems.get("basketballMatchList");
            for (BasketballMatchDO basketballMatchDO : basketballMatchList) {
                BasketballMatchDO basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                        .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                        .like(BasketballMatchDO::getHomeTeam, basketballMatchDO.getHomeTeam())
                        .like(BasketballMatchDO::getVisitingTeam, basketballMatchDO.getVisitingTeam()));
                //第二次判断
                if (ObjectUtil.isNull(basketballMatch)) {
                    basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                            .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                            .eq(BasketballMatchDO::getOpenTime, basketballMatchDO.getOpenTime()));
                }
                //第三次判断
                if (ObjectUtil.isNull(basketballMatch)) {
                    Date start = DateUtil.parse(DateUtil.today() + " 00:00:00");
                    Date end = DateUtil.parse(DateUtil.today() + " 23:59:59");
                    start = DateUtil.offsetDay(start, -3);
                    basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                            .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                            .ge(BasketballMatchDO::getCreateTime, start)
                            .le(BasketballMatchDO::getCreateTime, end)
                            .like(BasketballMatchDO::getHomeTeam, basketballMatchDO.getHomeTeam()).or()
                            .like(BasketballMatchDO::getVisitingTeam, basketballMatchDO.getVisitingTeam())
                    );
                }
                if (ObjectUtil.isNotNull(basketballMatch)) {
                    basketballMatch.setAward(basketballMatchDO.getAward());
                    basketballMatch.setHalfFullCourt(basketballMatchDO.getHalfFullCourt());
                    basketballMatch.setUpdateTime(new Date());
                    basketballMatchService.updateById(basketballMatch);
                }
            }

            //查询历史未开奖的比赛
            List<BasketballMatchDO> basketballList = basketballMatchService.list(new QueryWrapper<BasketballMatchDO>().lambda().isNull(BasketballMatchDO::getAward).isNull(BasketballMatchDO::getHalfFullCourt));
            if (CollUtil.isNotEmpty(basketballList)) {
                for (BasketballMatchDO basketballMatchDO : basketballList) {
                    try {
                        Document document = Jsoup.connect(CrawlingAddressConstant.URL7 + "?d=" + DateUtil.thisYear() + "-" + basketballMatchDO.getOpenTime().substring(0, basketballMatchDO.getOpenTime().indexOf(" ") + 1)).get();
                        Elements elements = document.select(".lea_list table.ld_table > tbody>tr");
                        for (int i = 1; i < elements.size(); i++) {
                            String number = elements.get(i).select("td:nth-child(1)").text().trim();
                            if (basketballMatchDO.getNumber().equals(number)) {
                                String str = elements.get(i).select("td:nth-child(12)").text();
                                String score = elements.get(i).select("td:nth-child(7)").text();
                                String result = "";
                                if (StrUtil.isNotBlank(score) && !score.equals("-")) {
                                    int[] scoreArr = StrUtil.splitToInt(score, ":");
                                    //判断让分后是客胜还是主胜
                                    if (scoreArr[0] > scoreArr[1] + Double.valueOf(str)) {
                                        result = "主负";
                                    } else {
                                        result = "主胜";
                                    }
                                }
                                //没出结果直接跳过
                                if (score.contains("-")) {
                                    continue;
                                }

                                basketballMatchDO.setAward(elements.get(i).select("td:nth-child(8)").text() + "," + result + "," + elements.get(i).select("td:nth-child(15)").text() + "," + elements.get(i).select("td:nth-child(19)").text());
                                basketballMatchDO.setHalfFullCourt(score);
                                basketballMatchService.updateById(basketballMatchDO);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL8)) {
            //北单数据
            List<BeiDanMatchDO> beiDanMatchList = resultItems.get("beiDanMatchList");
            for (BeiDanMatchDO beiDanMatchDO : beiDanMatchList) {
                BeiDanMatchDO beiDanMatch = beiDanMatchService.getOne(new QueryWrapper<BeiDanMatchDO>().lambda()
                        .eq(BeiDanMatchDO::getNumber, beiDanMatchDO.getNumber())
                        .eq(BeiDanMatchDO::getMatch, beiDanMatchDO.getMatch())
                        .like(BeiDanMatchDO::getHomeTeam, beiDanMatchDO.getHomeTeam())
                        .like(BeiDanMatchDO::getVisitingTeam, beiDanMatchDO.getVisitingTeam()));
                if (ObjectUtil.isNotNull(beiDanMatch)) {
                    beiDanMatchDO.setId(beiDanMatch.getId());
                    beiDanMatchDO.setUpdateTime(new Date());
                }
            }
            beiDanMatchService.saveOrUpdateBatch(beiDanMatchList);
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL9)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL10)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL11)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL12)) {
            //北单数据
            List<BeiDanMatchDO> beiDanMatchList = resultItems.get("beiDanMatchList");
            for (BeiDanMatchDO beiDanMatchDO : beiDanMatchList) {
                BeiDanMatchDO beiDanMatch = beiDanMatchService.getOne(new QueryWrapper<BeiDanMatchDO>().lambda()
                        .eq(BeiDanMatchDO::getNumber, beiDanMatchDO.getNumber())
                        .eq(BeiDanMatchDO::getMatch, beiDanMatchDO.getMatch())
                        .like(BeiDanMatchDO::getHomeTeam, beiDanMatchDO.getHomeTeam())
                        .like(BeiDanMatchDO::getVisitingTeam, beiDanMatchDO.getVisitingTeam()));
                if (ObjectUtil.isNotNull(beiDanMatch)) {
                    beiDanMatchDO.setId(beiDanMatch.getId());
                    beiDanMatchDO.setUpdateTime(new Date());
                } else {
                    continue;
                }
            }
            beiDanMatchService.updateBatchById(beiDanMatchList);
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL13)) {
            //北单开奖
            List<BeiDanMatchDO> beiDanMatchList = resultItems.get("beiDanMatchList");
            for (BeiDanMatchDO beiDanMatchDO : beiDanMatchList) {
                BeiDanMatchDO beiDanMatch = beiDanMatchService.getOne(new QueryWrapper<BeiDanMatchDO>().lambda()
                        .eq(BeiDanMatchDO::getNumber, beiDanMatchDO.getNumber())
                        .eq(BeiDanMatchDO::getMatch, beiDanMatchDO.getMatch())
                        .like(BeiDanMatchDO::getHomeTeam, beiDanMatchDO.getHomeTeam())
                        .like(BeiDanMatchDO::getVisitingTeam, beiDanMatchDO.getVisitingTeam()));

                if (ObjectUtil.isNotNull(beiDanMatch)) {
                    if (StrUtil.isBlank(beiDanMatch.getBonusOdds()) && StrUtil.isBlank(beiDanMatch.getAward()) && StrUtil.isBlank(beiDanMatch.getHalfFullCourt()) || beiDanMatch.getBonusOdds().indexOf("-") != -1) {
                        beiDanMatch.setUpdateTime(new Date());
                        beiDanMatch.setAward(beiDanMatchDO.getAward());
                        beiDanMatch.setHalfFullCourt(beiDanMatchDO.getHalfFullCourt());
                        beiDanMatch.setBonusOdds(beiDanMatchDO.getBonusOdds());
                        beiDanMatchService.updateById(beiDanMatch);
                    }
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL14)) {
            //存储爬取到的篮球的比赛数据
            List<BasketballMatchDO> basketballMatchList = resultItems.get("basketballMatchList");
            for (BasketballMatchDO basketballMatchDO : basketballMatchList) {
                BasketballMatchDO basketballMatch = basketballMatchService.getOne(new QueryWrapper<BasketballMatchDO>().lambda()
                        .eq(BasketballMatchDO::getNumber, basketballMatchDO.getNumber())
                        .eq(BasketballMatchDO::getMatch, basketballMatchDO.getMatch())
                        .eq(BasketballMatchDO::getStartTime, basketballMatchDO.getStartTime())
                        .eq(BasketballMatchDO::getOpenTime, basketballMatchDO.getOpenTime())
                        .like(BasketballMatchDO::getHomeTeam, basketballMatchDO.getHomeTeam())
                        .like(BasketballMatchDO::getVisitingTeam, basketballMatchDO.getVisitingTeam()));
                if (ObjectUtil.isNotNull(basketballMatch)) {
                    basketballMatch.setIsSingle("1");
                    basketballMatchService.updateById(basketballMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL15)) {
            List<BeiDanMatchDO> beiDanMatchList = resultItems.get("beiDanMatchList");
            for (BeiDanMatchDO beiDanMatchDO : beiDanMatchList) {
                BeiDanMatchDO beiDanMatch = beiDanMatchService.getOne(new QueryWrapper<BeiDanMatchDO>().lambda()
                        .eq(BeiDanMatchDO::getNumber, beiDanMatchDO.getNumber())
                        .eq(BeiDanMatchDO::getState, BettingStateEnum.YES.getKey()));
                if (ObjectUtil.isNotNull(beiDanMatch) && StrUtil.isBlank(beiDanMatch.getAnalysis())) {
                    beiDanMatch.setAnalysis(beiDanMatchDO.getAnalysis());
                    beiDanMatch.setUpdateTime(new Date());
                    beiDanMatchService.updateById(beiDanMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL18)) {
            List<WinBurdenMatchDO> winBurdenMatchList = resultItems.get("winBurdenMatchList");
            for (WinBurdenMatchDO winBurdenMatchDO : winBurdenMatchList) {
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchService.getOne(new QueryWrapper<WinBurdenMatchDO>().lambda()
                        .eq(WinBurdenMatchDO::getNumber, winBurdenMatchDO.getNumber())
                        .eq(WinBurdenMatchDO::getMatch, winBurdenMatchDO.getMatch())
                        .eq(WinBurdenMatchDO::getOpenTime, winBurdenMatchDO.getOpenTime())
                        .like(WinBurdenMatchDO::getHomeTeam, winBurdenMatchDO.getHomeTeam().substring(winBurdenMatchDO.getHomeTeam().indexOf("]") + 1))
                        .like(WinBurdenMatchDO::getVisitingTeam, winBurdenMatchDO.getVisitingTeam().substring(winBurdenMatchDO.getVisitingTeam().indexOf("]") + 1)));
                if (ObjectUtil.isNull(winBurdenMatch)) {
                    winBurdenMatchService.save(winBurdenMatchDO);
                } else {
                    //如果不为空就判断当前的分析链接是否有
                    if (StrUtil.isBlank(winBurdenMatch.getAnalysis())) {
                        //如果没有就到北单那里去查询在写入 到数据库
                        BeiDanMatchDO beiDanMatch = beiDanMatchService.getOne(new QueryWrapper<BeiDanMatchDO>().lambda()
                                .eq(BeiDanMatchDO::getState, BettingStateEnum.YES.getKey())
                                .like(BeiDanMatchDO::getHomeTeam, winBurdenMatchDO.getHomeTeam().substring(winBurdenMatchDO.getHomeTeam().indexOf("]") + 1))
                                .like(BeiDanMatchDO::getVisitingTeam, winBurdenMatchDO.getVisitingTeam().substring(winBurdenMatchDO.getVisitingTeam().indexOf("]") + 1))
                                .eq(BeiDanMatchDO::getMatch, winBurdenMatchDO.getMatch()));
                        if (ObjectUtil.isNotNull(beiDanMatch)) {
                            //写入数据库
                            winBurdenMatch.setAnalysis(beiDanMatch.getAnalysis());
                            winBurdenMatchService.updateById(winBurdenMatch);
                        }
                    }
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL19)) {
            List<WinBurdenMatchDO> winBurdenMatchList = resultItems.get("winBurdenMatchList");
            for (WinBurdenMatchDO winBurdenMatchDO : winBurdenMatchList) {
                WinBurdenMatchDO winBurdenMatch = winBurdenMatchService.getOne(new QueryWrapper<WinBurdenMatchDO>().lambda()
                        .eq(WinBurdenMatchDO::getIssueNo, winBurdenMatchDO.getIssueNo())
                        .like(WinBurdenMatchDO::getHomeTeam, winBurdenMatchDO.getHomeTeam()));
                if (StrUtil.isBlank(winBurdenMatch.getAward()) || winBurdenMatch.getMoneyAward().indexOf("--") != -1) {
                    winBurdenMatch.setUpdateTime(new Date());
                    winBurdenMatch.setAward(winBurdenMatchDO.getAward());
                    winBurdenMatch.setMoneyAward(winBurdenMatchDO.getMoneyAward());
                    winBurdenMatchService.updateById(winBurdenMatch);
                }
            }
        } else if (ObjectUtil.equal(url, CrawlingAddressConstant.URL21)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL22)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL23)
                || ObjectUtil.equal(url, CrawlingAddressConstant.URL24)) {
            List<OmitDO> omitList = resultItems.get("omitList");
            for (OmitDO omitDO : omitList) {
                OmitDO omit = omitService.getOne(new QueryWrapper<OmitDO>().lambda().eq(OmitDO::getType, omitDO.getType()));
                omitDO.setUpdateTime(new Date());
                if (ObjectUtil.isNull(omit)) {
                    omitDO.setCreateTime(new Date());
                    omitService.save(omitDO);
                } else {
                    omit.setRecord(omitDO.getRecord());
                    omitService.updateById(omit);
                }
            }
        }
    }
}
