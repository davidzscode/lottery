package com.qihang.common.util.reward;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.racingball.app.vo.BallCombinationVO;
import com.qihang.controller.racingball.app.vo.BallOptimizationVO;
import com.qihang.controller.winburden.dto.WinBurdenMatchDTO;

import java.util.*;

/**
 * 足球胜负彩14
 *
 * @author qihang
 */
public class WinBurdenUtil {

    /**
     * 计算组合
     *
     * @param ballCalculationDTO
     * @return
     */
    public static BallCalculationVO calculation(BallCalculationDTO ballCalculationDTO) {
        BallCalculationVO ballCalculation = new BallCalculationVO();

        List<BallOptimizationVO> heatOptimizationList = new ArrayList<>();
        Map<String, List<String>> makeUpMap = new HashMap<>();
        makeUp(ballCalculationDTO.getWinBurdenMatchList(), makeUpMap);

        for (String code : makeUpMap.get("makeUpList")) {
            BallOptimizationVO ballOptimization = new BallOptimizationVO();
            ballOptimization.setType("足球14场");
            ballOptimization.setNotes(ballCalculationDTO.getMultiple());
            List<BallCombinationVO> ballCombinationList = new ArrayList<>();
            List<String> list = Convert.toList(String.class, code);
            //开始组合
            List<WinBurdenMatchDTO> winBurdenMatchList = ballCalculationDTO.getWinBurdenMatchList();
            for (int i = 0; i < winBurdenMatchList.size(); i++) {
                BallCombinationVO ballCombination = new BallCombinationVO();
                ballCombination.setNumber(winBurdenMatchList.get(i).getNumber());
                ballCombination.setVisitingTeam(winBurdenMatchList.get(i).getVisitingTeam());
                ballCombination.setHomeTeam(winBurdenMatchList.get(i).getHomeTeam());
                ballCombination.setContent(list.get(i));
                ballCombinationList.add(ballCombination);
            }
            ballCalculation.setNotes(makeUpMap.get("makeUpList").size());
            ballOptimization.setBallCombinationList(ballCombinationList);
            heatOptimizationList.add(ballOptimization);
        }
        ballCalculation.setAverageOptimizationList(heatOptimizationList);
        return ballCalculation;
    }

    /**
     * 组配下注结果
     *
     * @param winBurdenMatchList
     * @param makeUpMap
     * @return
     */
    private static Map<String, List<String>> makeUp(List<WinBurdenMatchDTO> winBurdenMatchList, Map<String, List<String>> makeUpMap) {
        //开奖list
        List<String> openEncourageList = new ArrayList<>();
        //组合list
        List<String> makeUpList = new ArrayList<>();

        List<Map<String, Object>> oneList = winBurdenMatchList.get(0).getNotLetOddsList();
        List<Map<String, Object>> twoList = winBurdenMatchList.get(1).getNotLetOddsList();
        List<Map<String, Object>> threeList = winBurdenMatchList.get(2).getNotLetOddsList();
        List<Map<String, Object>> fourList = winBurdenMatchList.get(3).getNotLetOddsList();
        List<Map<String, Object>> fiveList = winBurdenMatchList.get(4).getNotLetOddsList();
        List<Map<String, Object>> sixList = winBurdenMatchList.get(5).getNotLetOddsList();
        List<Map<String, Object>> sevenList = winBurdenMatchList.get(6).getNotLetOddsList();
        List<Map<String, Object>> eightList = winBurdenMatchList.get(7).getNotLetOddsList();
        List<Map<String, Object>> nineList = winBurdenMatchList.get(8).getNotLetOddsList();
        List<Map<String, Object>> tenList = winBurdenMatchList.get(9).getNotLetOddsList();
        List<Map<String, Object>> elevenList = winBurdenMatchList.get(10).getNotLetOddsList();
        List<Map<String, Object>> twelveList = winBurdenMatchList.get(11).getNotLetOddsList();
        List<Map<String, Object>> thirteenList = winBurdenMatchList.get(12).getNotLetOddsList();
        List<Map<String, Object>> fourteenList = winBurdenMatchList.get(13).getNotLetOddsList();
        for (Map<String, Object> one : oneList) {
            for (Map<String, Object> two : twoList) {
                for (Map<String, Object> three : threeList) {
                    for (Map<String, Object> four : fourList) {
                        for (Map<String, Object> five : fiveList) {
                            for (Map<String, Object> six : sixList) {
                                for (Map<String, Object> seven : sevenList) {
                                    for (Map<String, Object> eight : eightList) {
                                        for (Map<String, Object> nine : nineList) {
                                            for (Map<String, Object> ten : tenList) {
                                                for (Map<String, Object> eleven : elevenList) {
                                                    for (Map<String, Object> twelve : twelveList) {
                                                        for (Map<String, Object> thirteen : thirteenList) {
                                                            for (Map<String, Object> fourteen : fourteenList) {
                                                                String makeUp = one.get("describe") + "(" + one.get("odds") + ")" + "," + two.get("describe") + "(" + two.get("odds") + ")" + "," + three.get("describe") + "(" + three.get("odds") + ")" + "," + four.get("describe") + "(" + four.get("odds") + ")" + "," + five.get("describe") + "(" + five.get("odds") + ")" + "," + six.get("describe") + "(" + six.get("odds") + ")" + "," + seven.get("describe") + "(" + seven.get("odds") + ")" + "," + eight.get("describe") + "(" + eight.get("odds") + ")" + "," + nine.get("describe") + "(" + nine.get("odds") + ")" + "," + ten.get("describe") + "(" + ten.get("odds") + ")" + "," + eleven.get("describe") + "(" + eleven.get("odds") + ")" + "," + twelve.get("describe") + "(" + twelve.get("odds") + ")" + "," + thirteen.get("describe") + "(" + thirteen.get("odds") + ")" + "," + fourteen.get("describe") + "(" + fourteen.get("odds") + ")";
                                                                makeUpList.add(makeUp);
                                                                String openEncourage = one.get("describe") + "," + two.get("describe") + "," + three.get("describe") + "," + four.get("describe") + "," + five.get("describe") + "," + six.get("describe") + "," + seven.get("describe") + "," + eight.get("describe") + "," + nine.get("describe") + "," + ten.get("describe") + "," + eleven.get("describe") + "," + twelve.get("describe") + "," + thirteen.get("describe") + "," + fourteen.get("describe");
                                                                openEncourageList.add(openEncourage);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        makeUpMap.put("makeUpList", makeUpList);
        makeUpMap.put("openEncourageList", openEncourageList);
        return makeUpMap;
    }

    /**
     * @param winBurdenMatchList 下注列表
     * @param multiple           倍数
     * @param str                开奖结果
     * @param firstPrizeBonus    一等奖奖金
     * @param secondPrizeBonus   二等奖奖金
     * @return
     */
    public static Double award(List<WinBurdenMatchDTO> winBurdenMatchList, Integer multiple, List<String> str, Double firstPrizeBonus, Double secondPrizeBonus) {
        Double price = 0.0;
        Map<String, List<String>> makeUpMap = new HashMap<>();
        makeUp(winBurdenMatchList, makeUpMap);
        List<String> openEncourageList = makeUpMap.get("openEncourageList");
        String authorityOpenEncourage = StrUtil.join(",", str);
        for (String openEncourage : openEncourageList) {
            //一等奖14场全部中
            if (openEncourage.equals(authorityOpenEncourage)) {
                price += firstPrizeBonus * multiple;
                continue;
            }
            //二等奖中13场
            String oneResult = "[\\u4E00-\\u9FFF],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String twoResult = "[" + str.get(0) + "],[\\u4E00-\\u9FFF],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String threeResult = "[" + str.get(0) + "],[" + str.get(1) + "],[\\u4E00-\\u9FFF],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String fourResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[\\u4E00-\\u9FFF],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String fiveResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[\\u4E00-\\u9FFF],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String sixResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[\\u4E00-\\u9FFF],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String sevenResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[\\u4E00-\\u9FFF],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String eightResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[\\u4E00-\\u9FFF],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String nineResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[\\u4E00-\\u9FFF],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String tenResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[\\u4E00-\\u9FFF],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String elevenResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[\\u4E00-\\u9FFF],[" + str.get(11) + "],[" + str.get(12) + "],[" + str.get(13) + "]";
            String twelveResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[\\u4E00-\\u9FFF],[" + str.get(12) + "],[" + str.get(13) + "]";
            String thirteenResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[\\u4E00-\\u9FFF],[" + str.get(13) + "]";
            String fourteenResult = "[" + str.get(0) + "],[" + str.get(1) + "],[" + str.get(2) + "],[" + str.get(3) + "],[" + str.get(4) + "],[" + str.get(5) + "],[" + str.get(6) + "],[" + str.get(7) + "],[" + str.get(8) + "],[" + str.get(9) + "],[" + str.get(10) + "],[" + str.get(11) + "],[" + str.get(12) + "],[\\u4E00-\\u9FFF]";

            if (openEncourage.matches(oneResult) ||
                    openEncourage.matches(twoResult) ||
                    openEncourage.matches(threeResult) ||
                    openEncourage.matches(fourResult) ||
                    openEncourage.matches(fiveResult) ||
                    openEncourage.matches(sixResult) ||
                    openEncourage.matches(sevenResult) ||
                    openEncourage.matches(eightResult) ||
                    openEncourage.matches(nineResult) ||
                    openEncourage.matches(tenResult) ||
                    openEncourage.matches(elevenResult) ||
                    openEncourage.matches(twelveResult) ||
                    openEncourage.matches(thirteenResult) ||
                    openEncourage.matches(fourteenResult)) {
                price += secondPrizeBonus * multiple;
                continue;
            }
        }
        return price;
    }
}
