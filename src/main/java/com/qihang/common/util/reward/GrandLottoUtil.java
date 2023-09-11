package com.qihang.common.util.reward;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.qihang.controller.grandlotto.dto.GrandLottoObjDTO;
import com.qihang.controller.winburden.dto.WinBurdenMatchDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class GrandLottoUtil {

    /**
     * redList 红球
     * blueList 蓝球
     */
    public static List<String> calculation(List<GrandLottoObjDTO> redListDTO, List<GrandLottoObjDTO> blueListDTO) {
        Map<String, List<String>> bluemakeUpMap = new HashMap<>();
        redMakeUp(redListDTO, bluemakeUpMap);

        Map<String, List<String>> redmakeUpMap = new HashMap<>();
        blueMakeUp(blueListDTO, redmakeUpMap);

        List<String> newblueList = new ArrayList();
        for (String blueList : bluemakeUpMap.get("makeUpList")) {
            List<String> bluesList = new ArrayList();
            for (String s : blueList.split(",")) {
                if (!"X".equals(s)) {
                    bluesList.add(s);
                }
            }
            newblueList.add(StringUtils.join(bluesList, ","));
        }

        List<String> newredList = new ArrayList();
        for (String redList : redmakeUpMap.get("makeUpList")) {
            List<String> redsList = new ArrayList();
            for (String s : redList.split(",")) {
                if (!"X".equals(s)) {
                    redsList.add(s);
                }
            }
            newredList.add(StringUtils.join(redsList, ","));
        }

        List<String> rlist = new ArrayList();
        for (String bstring : newblueList) {
            for (String rstring : newredList) {
                rlist.add(bstring + "," + rstring);
            }
        }
        return rlist;
    }

    /**
     * @param redListDTO       红球
     * @param blueListDTO      蓝球
     * @param target           开奖号码
     * @param firstPrizeBonus  一等奖
     * @param secondPrizeBonus 二等奖
     * @return
     */
    public static Long award(List<GrandLottoObjDTO> redListDTO, List<GrandLottoObjDTO> blueListDTO, String target, String firstPrizeBonus, String secondPrizeBonus) {
        List<String> list = GrandLottoUtil.calculation(redListDTO, blueListDTO);
        Long price = 0L;
        for (String str : list) {
            List<String> strList = Convert.toList(String.class, str);
            List<String> targetList = Arrays.asList(target.split(","));
            int count = 0;
            for (String s : targetList) {
                if (strList.contains(s)) {
                    count++;
                    continue;
                }
            }
            //一等奖：投注号码与当期开奖号码全部相同(顺序不限，下同)，即中奖；
            if (count == 7) {
                price += Long.valueOf(firstPrizeBonus);
                continue;
            }
            String regexTwo1 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexTwo2 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            //二等奖：投注号码与当期开奖号码中的五个前区号码及任意一个后区号码相同，即中奖
            if (str.matches(regexTwo1) || str.matches(regexTwo2)) {
                price += Long.valueOf(secondPrizeBonus);
                continue;
            }
            //三等奖：投注号码与当期开奖号码中的五个前区号码相同，即中奖；
            String regexThree1 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," + "[0-9][0-9]";
            if (str.matches(regexThree1)) {
                price += Long.valueOf(10000);
                continue;
            }
            //四等奖：投注号码与当期开奖号码中的任意四个前区号码及两个后区号码相同，即中奖；
            String regexFour1 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexFour2 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexFour3 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexFour4 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexFour5 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            if (str.matches(regexFour1) || str.matches(regexFour2) || str.matches(regexFour3) || str.matches(regexFour4) || str.matches(regexFour5)) {
                price += Long.valueOf(3000);
                continue;
            }
            //五等奖：投注号码与当期开奖号码中的任意四个前区号码及任意一个后区号码相同，即中奖；
            String regexFive1 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";

            String regexFive2 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";

            String regexFive3 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";

            String regexFive4 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";

            String regexFive5 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";

            String regexFive6 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";

            String regexFive7 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";

            String regexFive8 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";

            String regexFive9 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";

            String regexFive10 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            if (str.matches(regexFive1) || str.matches(regexFive2) || str.matches(regexFive3) || str.matches(regexFive4) || str.matches(regexFive5) || str.matches(regexFive6) || str.matches(regexFive7) || str.matches(regexFive8) || str.matches(regexFive9) || str.matches(regexFive10)) {
                price += Long.valueOf(300);
                continue;
            }
            //六等奖：投注号码与当期开奖号码中的任意三个前区号码及两个后区号码相同，即中奖；
            String regexSix1 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix2 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix3 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix4 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix5 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix6 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix7 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix8 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix9 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexSix10 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            if (str.matches(regexSix1) || str.matches(regexSix2) || str.matches(regexSix3) || str.matches(regexSix4) || str.matches(regexSix5) || str.matches(regexSix6) || str.matches(regexSix7) || str.matches(regexSix8) || str.matches(regexSix9) || str.matches(regexSix10)) {
                price += Long.valueOf(200);
                continue;
            }
            //七等奖：投注号码与当期开奖号码中的任意四个前区号码相同，即中奖；
            String regexSeven1 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexSeven2 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexSeven3 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexSeven4 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexSeven5 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            if (str.matches(regexSeven1) || str.matches(regexSeven2) || str.matches(regexSeven3) || str.matches(regexSeven4) || str.matches(regexSeven5)) {
                price += Long.valueOf(100);
                continue;
            }
            //八等奖：投注号码与当期开奖号码中的任意三个前区号码及任意一个后区号码相同，或者任意两个前区号码及两个后区号码相同，即中奖;
            String regexEight1 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight2 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight3 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight4 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight5 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight6 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight7 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight8 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight9 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight10 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight11 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight12 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight13 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight14 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight15 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight16 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight17 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight18 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight19 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight20 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexEight21 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight22 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight23 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight24 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight25 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight26 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight27 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight28 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight29 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexEight30 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            if (str.matches(regexEight1)
                    || str.matches(regexEight2)
                    || str.matches(regexEight3)
                    || str.matches(regexEight4)
                    || str.matches(regexEight5)
                    || str.matches(regexEight6)
                    || str.matches(regexEight7)
                    || str.matches(regexEight8)
                    || str.matches(regexEight9)
                    || str.matches(regexEight10)
                    || str.matches(regexEight11)
                    || str.matches(regexEight12)
                    || str.matches(regexEight13)
                    || str.matches(regexEight14)
                    || str.matches(regexEight15)
                    || str.matches(regexEight16)
                    || str.matches(regexEight17)
                    || str.matches(regexEight18)
                    || str.matches(regexEight19)
                    || str.matches(regexEight20)
                    || str.matches(regexEight21)
                    || str.matches(regexEight22)
                    || str.matches(regexEight23)
                    || str.matches(regexEight24)
                    || str.matches(regexEight25)
                    || str.matches(regexEight26)
                    || str.matches(regexEight27)
                    || str.matches(regexEight28)
                    || str.matches(regexEight29)
                    || str.matches(regexEight30)
            ) {
                price += Long.valueOf(15);
                continue;
            }
            //九等奖：投注号码与当期开奖号码中的任意三个前区号码相同，或者任意一个前区号码及两个后区号码相同，或者任意两个前区号码及任意一个后区号码相同，或者两个后区号码相同，即中奖。
            String regexNine1 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine2 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine3 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine4 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine5 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine6 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine7 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine8 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine9 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine10 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]";
            String regexNine11 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine12 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine13 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine14 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine15 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine16 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine17 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine18 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine19 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]" +
                    "[0-9][0-9]";
            String regexNine20 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine21 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine22 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine23 = "[" + tear(targetList.get(0))[0] + "][" + tear(targetList.get(0))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine24 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine25 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine26 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine27 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine28 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine29 = "[0-9][0-9]," +
                    "[" + tear(targetList.get(1))[0] + "][" + tear(targetList.get(1))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine30 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine31 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine32 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine33 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(2))[0] + "][" + tear(targetList.get(2))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine34 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            String regexNine35 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(3))[0] + "][" + tear(targetList.get(3))[1] + "]," +
                    "[" + tear(targetList.get(4))[0] + "][" + tear(targetList.get(4))[1] + "]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[0-9][0-9]";
            String regexNine36 = "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[0-9][0-9]," +
                    "[" + tear(targetList.get(5))[0] + "][" + tear(targetList.get(5))[1] + "]," +
                    "[" + tear(targetList.get(6))[0] + "][" + tear(targetList.get(6))[1] + "]";
            if (str.matches(regexSix1)
                    || str.matches(regexNine1)
                    || str.matches(regexNine2)
                    || str.matches(regexNine3)
                    || str.matches(regexNine4)
                    || str.matches(regexNine5)
                    || str.matches(regexNine6)
                    || str.matches(regexNine7)
                    || str.matches(regexNine8)
                    || str.matches(regexNine9)
                    || str.matches(regexNine10)
                    || str.matches(regexNine11)
                    || str.matches(regexNine12)
                    || str.matches(regexNine13)
                    || str.matches(regexNine14)
                    || str.matches(regexNine15)
                    || str.matches(regexNine16)
                    || str.matches(regexNine17)
                    || str.matches(regexNine18)
                    || str.matches(regexNine19)
                    || str.matches(regexNine20)
                    || str.matches(regexNine21)
                    || str.matches(regexNine22)
                    || str.matches(regexNine23)
                    || str.matches(regexNine24)
                    || str.matches(regexNine25)
                    || str.matches(regexNine26)
                    || str.matches(regexNine27)
                    || str.matches(regexNine28)
                    || str.matches(regexNine29)
                    || str.matches(regexNine30)
                    || str.matches(regexNine31)
                    || str.matches(regexNine32)
                    || str.matches(regexNine33)
                    || str.matches(regexNine34)
                    || str.matches(regexNine35)
                    || str.matches(regexNine36)
            ) {
                price += Long.valueOf(5);
                continue;
            }
        }
        return price;
    }

    /**
     * 将一个数字拆成二个数字
     *
     * @param str
     * @return
     */
    private static String[] tear(String str) {
        String[] arr = new String[2];
        Integer num = Integer.valueOf(str);
        String g = String.valueOf(num % 10);
        String s = String.valueOf(num / 10 % 10);
        arr[0] = s;
        arr[1] = g;
        return arr;
    }

    /**
     * 组配下注结果
     *
     * @param blueList
     * @param makeUpMap
     * @return
     */
    private static Map<String, List<String>> redMakeUp(List<GrandLottoObjDTO> blueList, Map<String, List<String>> makeUpMap) {
        //组合list
        List<String> makeUpList = new ArrayList<>();

        int x = 0;

        List<GrandLottoObjDTO> rList = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            String y = "" + (i + 1);
            if (i < 9) {
                y = "0" + (i + 1);
            }

            if (blueList.size() > x && blueList.get(x).getNum().equals(y)) {
                if (!blueList.get(x).getIsGallbladder()) {
                    blueList.get(x).setNum(blueList.get(x).getNum() + ",X");
                }
                rList.add(blueList.get(x));
                x++;
            } else {
                GrandLottoObjDTO grandLottoObjDTO = new GrandLottoObjDTO();
                grandLottoObjDTO.setIsGallbladder(false);
                grandLottoObjDTO.setNum("X");

                rList.add(grandLottoObjDTO);
            }
        }

        for (String B1 : rList.get(0).getNum().split(",")) {
            for (String B2 : rList.get(1).getNum().split(",")) {
                for (String B3 : rList.get(2).getNum().split(",")) {
                    for (String B4 : rList.get(3).getNum().split(",")) {
                        for (String B5 : rList.get(4).getNum().split(",")) {
                            for (String B6 : rList.get(5).getNum().split(",")) {
                                for (String B7 : rList.get(6).getNum().split(",")) {
                                    for (String B8 : rList.get(7).getNum().split(",")) {
                                        for (String B9 : rList.get(8).getNum().split(",")) {
                                            for (String B10 : rList.get(9).getNum().split(",")) {
                                                for (String B11 : rList.get(10).getNum().split(",")) {
                                                    for (String B12 : rList.get(11).getNum().split(",")) {
                                                        for (String B13 : rList.get(12).getNum().split(",")) {
                                                            for (String B14 : rList.get(13).getNum().split(",")) {
                                                                for (String B15 : rList.get(14).getNum().split(",")) {
                                                                    for (String B16 : rList.get(15).getNum().split(",")) {
                                                                        for (String B17 : rList.get(16).getNum().split(",")) {
                                                                            for (String B18 : rList.get(17).getNum().split(",")) {
                                                                                for (String B19 : rList.get(18).getNum().split(",")) {
                                                                                    for (String B20 : rList.get(19).getNum().split(",")) {
                                                                                        for (String B21 : rList.get(20).getNum().split(",")) {
                                                                                            for (String B22 : rList.get(21).getNum().split(",")) {
                                                                                                for (String B23 : rList.get(22).getNum().split(",")) {
                                                                                                    for (String B24 : rList.get(23).getNum().split(",")) {
                                                                                                        for (String B25 : rList.get(24).getNum().split(",")) {
                                                                                                            for (String B26 : rList.get(25).getNum().split(",")) {
                                                                                                                for (String B27 : rList.get(26).getNum().split(",")) {
                                                                                                                    for (String B28 : rList.get(27).getNum().split(",")) {
                                                                                                                        for (String B29 : rList.get(28).getNum().split(",")) {
                                                                                                                            for (String B30 : rList.get(29).getNum().split(",")) {
                                                                                                                                for (String B31 : rList.get(30).getNum().split(",")) {
                                                                                                                                    for (String B32 : rList.get(31).getNum().split(",")) {
                                                                                                                                        for (String B33 : rList.get(32).getNum().split(",")) {
                                                                                                                                            for (String B34 : rList.get(33).getNum().split(",")) {
                                                                                                                                                for (String B35 : rList.get(34).getNum().split(",")) {
                                                                                                                                                    String str = B1 + "," + B2 + "," + B3 + "," + B4 + "," + B5 + "," + B6 + "," + B7 + "," + B8 + "," + B9 + "," + B10 + "," + B11 + "," + B12 + "," + B13 + "," + B14 + "," + B15 + "," + B16 + "," + B17 + "," + B18 + "," + B19 + "," + B20 + "," + B21 + "," + B22 + "," + B23 + "," + B24 + "," + B25 + "," + B26 + "," + B27 + "," + B28 + "," + B29 + "," + B30 + "," + B31 + "," + B32 + "," + B33 + "," + B34 + "," + B35;
                                                                                                                                                    makeUpList.add(str);
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
            if (iCunt == 30) {
                if (makeUpRSet.add(s)) {
                    makeUpRList.add(s);
                }
            }
        }
        makeUpMap.put("makeUpList", makeUpRList);
        return makeUpMap;
    }

    /**
     * 组配下注结果
     *
     * @param blueList
     * @param makeUpMap
     * @return
     */
    private static Map<String, List<String>> blueMakeUp(List<GrandLottoObjDTO> blueList, Map<String, List<String>> makeUpMap) {
        //组合list
        List<String> makeUpList = new ArrayList<>();

        int x = 0;

        List<GrandLottoObjDTO> rList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String y = "" + (i + 1);
            if (i < 9) {
                y = "0" + (i + 1);
            }

            if (blueList.size() > x && blueList.get(x).getNum().equals(y)) {
                if (!blueList.get(x).getIsGallbladder()) {
                    blueList.get(x).setNum(blueList.get(x).getNum() + ",X");
                }
                rList.add(blueList.get(x));
                x++;
            } else {
                GrandLottoObjDTO grandLottoObjDTO = new GrandLottoObjDTO();
                grandLottoObjDTO.setIsGallbladder(false);
                grandLottoObjDTO.setNum("X");

                rList.add(grandLottoObjDTO);
            }
        }

        for (String B1 : rList.get(0).getNum().split(",")) {
            for (String B2 : rList.get(1).getNum().split(",")) {
                for (String B3 : rList.get(2).getNum().split(",")) {
                    for (String B4 : rList.get(3).getNum().split(",")) {
                        for (String B5 : rList.get(4).getNum().split(",")) {
                            for (String B6 : rList.get(5).getNum().split(",")) {
                                for (String B7 : rList.get(6).getNum().split(",")) {
                                    for (String B8 : rList.get(7).getNum().split(",")) {
                                        for (String B9 : rList.get(8).getNum().split(",")) {
                                            for (String B10 : rList.get(9).getNum().split(",")) {
                                                for (String B11 : rList.get(10).getNum().split(",")) {
                                                    for (String B12 : rList.get(11).getNum().split(",")) {
                                                        String str = B1 + "," + B2 + "," + B3 + "," + B4 + "," + B5 + "," + B6 + "," + B7 + "," + B8 + "," + B9 + "," + B10 + "," + B11 + "," + B12;
                                                        makeUpList.add(str);
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
            if (iCunt == 10) {
                if (makeUpRSet.add(s)) {
                    makeUpRList.add(s);
                }
            }
        }
        makeUpMap.put("makeUpList", makeUpRList);
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
