package com.qihang.common.util.reward;

import cn.hutool.core.util.StrUtil;
import com.qihang.controller.racingball.app.dto.BallCalculationDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.racingball.app.vo.BallCombinationVO;
import com.qihang.controller.racingball.app.vo.BallOptimizationVO;
import com.qihang.controller.winburden.dto.WinBurdenMatchDTO;

import java.util.*;

/**
 * 任选九
 *
 * @author qihang
 */
public class RenJiuUtil {

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
            ballOptimization.setType("任选九");
            ballOptimization.setNotes(ballCalculationDTO.getMultiple());
            List<BallCombinationVO> ballCombinationList = new ArrayList<>();
//            List<String> list = Convert.toList(String.class, code);
            //开始组合
            List<WinBurdenMatchDTO> winBurdenMatchList = ballCalculationDTO.getWinBurdenMatchList();
            int i = 0;
            String[] str = code.split(",");
            for (String s : str) {
                if (!"X(0)".equals(s)) {
                    BallCombinationVO ballCombination = new BallCombinationVO();
                    ballCombination.setNumber((i + 1) + "");
                    for (WinBurdenMatchDTO winBurdenMatchDTO : winBurdenMatchList) {
                        if (ballCombination.getNumber().equals(winBurdenMatchDTO.getNumber())) {
                            ballCombination.setVisitingTeam(winBurdenMatchDTO.getVisitingTeam());
                            ballCombination.setHomeTeam(winBurdenMatchDTO.getHomeTeam());
                        }
                    }
                    ballCombination.setContent(s);
                    ballCombinationList.add(ballCombination);
                }
                i++;
            }


            ballCalculation.setNotes(makeUpMap.get("makeUpList").size());
            ballOptimization.setBallCombinationList(ballCombinationList);
            heatOptimizationList.add(ballOptimization);
        }
        ballCalculation.setAverageOptimizationList(heatOptimizationList);
        return ballCalculation;
    }

    /**
     * @param winBurdenMatchList 下注列表
     * @param multiple           倍数
     * @param str                开奖结果
     * @param firstPrizeBonus    一等奖奖金
     * @return
     */
    public static Double award(List<WinBurdenMatchDTO> winBurdenMatchList, Integer multiple, List<String> str, Double firstPrizeBonus) {
        Double price = 0.0;
        Map<String, List<String>> makeUpMap = new HashMap<>();
        makeUp(winBurdenMatchList, makeUpMap);
        List<String> openEncourageList = makeUpMap.get("openEncourageList");
        String authorityOpenEncourage = StrUtil.join(",", str);
        //获得中奖的单号
        int number = encourage(authorityOpenEncourage,openEncourageList);
        return number*multiple*firstPrizeBonus;
    }
    /**
     * 获得中奖的单号
     * */
    public static int encourage(String target,List<String> rlist){
        List<String> targetLsit = Arrays.asList(target.split(","));
        Map map = new HashMap();
        for(int i=0;i<targetLsit.size();i++){
            map.put(i,targetLsit.get(i));
        }

        List arrayList = new ArrayList();

        for(String str : rlist){
            //开奖单计算器
            int x = 0;
            //订单匹配号码计算器
            int y = 0;
            String[] split = str.split(",");
            for (String string : split){
                if(string.equals(map.get(x))){
                    y = y + 1;
                    if(y == 9){
                        arrayList.add(str);
                        continue;
                    }
                }
                x = x + 1;
            }
        }

        return arrayList.size();
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

        int x = 0;
        List<WinBurdenMatchDTO> rList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            if (winBurdenMatchList.size() > x && winBurdenMatchList.get(x).getNumber().equals((i + 1) + "")) {
                rList.add(winBurdenMatchList.get(x));
                x++;
            } else {
                WinBurdenMatchDTO winBurdenMatchDTO = new WinBurdenMatchDTO();
                winBurdenMatchDTO.setNumber(i + "");
                winBurdenMatchDTO.setIsGallbladder(false);
                List<Map<String, Object>> mList = new ArrayList<>();
                Map m = new HashMap();
                m.put("describe", "X");
                m.put("odds", 0);
                mList.add(m);
                winBurdenMatchDTO.setNotLetOddsList(mList);
                rList.add(winBurdenMatchDTO);
            }
        }

        List<Map<String, Object>> oneList = rList.get(0).getNotLetOddsList();
        List<Map<String, Object>> twoList = rList.get(1).getNotLetOddsList();
        List<Map<String, Object>> threeList = rList.get(2).getNotLetOddsList();
        List<Map<String, Object>> fourList = rList.get(3).getNotLetOddsList();
        List<Map<String, Object>> fiveList = rList.get(4).getNotLetOddsList();
        List<Map<String, Object>> sixList = rList.get(5).getNotLetOddsList();
        List<Map<String, Object>> sevenList = rList.get(6).getNotLetOddsList();
        List<Map<String, Object>> eightList = rList.get(7).getNotLetOddsList();
        List<Map<String, Object>> nineList = rList.get(8).getNotLetOddsList();
        List<Map<String, Object>> tenList = rList.get(9).getNotLetOddsList();
        List<Map<String, Object>> elevenList = rList.get(10).getNotLetOddsList();
        List<Map<String, Object>> twelveList = rList.get(11).getNotLetOddsList();
        List<Map<String, Object>> thirteenList = rList.get(12).getNotLetOddsList();
        List<Map<String, Object>> fourteenList = rList.get(13).getNotLetOddsList();
        if (!rList.get(0).getIsGallbladder() && !"X".equals(rList.get(0).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            oneList.add(map);
        }
        if (!rList.get(1).getIsGallbladder() && !"X".equals(rList.get(1).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            twoList.add(map);
        }
        if (!rList.get(2).getIsGallbladder() && !"X".equals(rList.get(2).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            threeList.add(map);
        }
        if (!rList.get(3).getIsGallbladder() && !"X".equals(rList.get(3).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            fourList.add(map);
        }
        if (!rList.get(4).getIsGallbladder() && !"X".equals(rList.get(4).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            fiveList.add(map);
        }
        if (!rList.get(5).getIsGallbladder() && !"X".equals(rList.get(5).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            sixList.add(map);
        }
        if (!rList.get(6).getIsGallbladder() && !"X".equals(rList.get(6).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            sevenList.add(map);
        }
        if (!rList.get(7).getIsGallbladder() && !"X".equals(rList.get(7).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            eightList.add(map);
        }
        if (!rList.get(8).getIsGallbladder() && !"X".equals(rList.get(8).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            nineList.add(map);
        }
        if (!rList.get(9).getIsGallbladder() && !"X".equals(rList.get(9).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            tenList.add(map);
        }
        if (!rList.get(10).getIsGallbladder() && !"X".equals(rList.get(10).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            elevenList.add(map);
        }
        if (!rList.get(11).getIsGallbladder() && !"X".equals(rList.get(11).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            twelveList.add(map);
        }
        if (!rList.get(12).getIsGallbladder() && !"X".equals(rList.get(12).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            thirteenList.add(map);
        }
        if (!rList.get(13).getIsGallbladder() && !"X".equals(rList.get(13).getNotLetOddsList().get(0).get("describe"))) {
            Map map = new HashMap();
            map.put("describe", "X");
            map.put("odds", 0);
            fourteenList.add(map);
        }
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

        Set<String> makeUpRSet = new HashSet<>();
        List<String> makeUpRList = new ArrayList<>();
        for (String s : makeUpList) {
            int iCunt = getStrCunt(s, "X");
            if (iCunt == 5) {
                if (makeUpRSet.add(s)) {
                    makeUpRList.add(s);
                }
            }
        }

        Set<String> openEncourageRSet = new HashSet<>();
        List<String> openEncourageRList = new ArrayList<>();
        for (String s : openEncourageList) {
            int iCunt = getStrCunt(s, "X");
            if (iCunt == 5) {
                if (openEncourageRSet.add(s)) {
                    openEncourageRList.add(s);
                }
            }
        }

        makeUpMap.put("makeUpList", makeUpRList);
        makeUpMap.put("openEncourageList", openEncourageRList);
        return makeUpMap;
    }

    private static int getStrCunt(String mainStr, String subStr) {
        // 声明一个要返回的变量
        int count = 0;
        // 声明一个初始的下标，从初始位置开始查找
        int index = 0;
        // 获取主数据的长度
        int mainStrLength = mainStr.length();
        // 获取要查找的数据长度
        int subStrLength = subStr.length();
        // 如果要查找的数据长度大于主数据的长度则返回0
        if (subStrLength > mainStrLength) {
            return 0;
        }
        // 循环使用indexOf查找出现的下标，如果出现一次则count++
        while ((index = mainStr.indexOf(subStr, index)) != -1) {
            count++;
            // 从找到的位置下标加上要查找的字符串长度，让指针往后移动继续查找
            index += subStrLength;
        }
        return count;
    }
}
