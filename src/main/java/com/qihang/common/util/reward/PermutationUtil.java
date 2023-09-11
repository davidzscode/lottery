package com.qihang.common.util.reward;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qihang.controller.permutation.app.vo.PermutationVO;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-01 22:08
 */
public class PermutationUtil {

    private static final Long price1 = 1040L;
    private static final Long price2 = 346L;
    private static final Long price3 = 173L;
    private static final Long price4 = 100000L;


    /**
     * 数字彩组合
     *
     * @param type        类型 排列3 排列5 七星彩
     * @param mode        模式
     * @param stageNumber 期号
     * @return
     */
    public static List<PermutationVO> makeUp(
            String type
            , String mode
            , Integer stageNumber
            , Integer times
            , Object[]... args) {
        List<PermutationVO> list = new ArrayList<>();
        //排列3
        if (type.equals(LotteryOrderTypeEnum.ARRAY.getKey())) {
            //直选
            if (mode.equals("0")) {
                for (int i = 0; i < args[0].length; i++) {
                    //通过第二层循环控制十位的数字    array[j]表示十位的值
                    for (int j = 0; j < args[1].length; j++) {
                        //通过第三层循环控制个位的数字   array[k]表示个位的值
                        for (int k = 0; k < args[2].length; k++) {
                            PermutationVO permutation = new PermutationVO();
                            permutation.setMode(mode);
                            permutation.setContent(args[0][i] + "," + args[1][j] + "," + args[2][k]);
                            permutation.setStageNumber(stageNumber);
                            permutation.setForecastBonus(new BigDecimal(price1 * times).setScale(2, RoundingMode.HALF_UP));
                            list.add(permutation);
                        }
                    }
                }
            } else if (mode.equals("1")) {
                //是否选了胆
                Boolean flag = false;
                //选中的胆的数字
                String dan = "";
                List<Object> objectList = Arrays.asList(args[0]);
                for (Object obj : objectList) {
                    JSONObject js = JSONUtil.parseObj(obj);
                    //判断是否选中了胆
                    if ((Boolean) js.get("isGallbladder")) {
                        flag = true;
                        dan = String.valueOf(js.get("num"));
                    }
                }
                List<String> contentList = new ArrayList<>();
                if (flag) {
                    //胆处理
                    for (int i = 0; i < args[0].length; i++) {
                        for (int j = i + 1; j < args[0].length; j++) {
                            JSONObject js = JSONUtil.parseObj(args[0][i]);
                            JSONObject js1 = JSONUtil.parseObj(args[0][j]);
                            String a = js.get("num").toString();
                            String b = js1.get("num").toString();
                            //过滤掉不是胆的组合
                            if (!a.equals(dan) && !b.equals(dan)) {
                                continue;
                            }
                            contentList.add(a + "," + a + "," + b);
                            contentList.add(a + "," + b + "," + a);
                            contentList.add(a + "," + b + "," + b);
                            contentList.add(b + "," + a + "," + b);
                            contentList.add(b + "," + b + "," + a);
                            contentList.add(b + "," + a + "," + a);
                        }
                    }
                } else {
                    for (int i = 0; i < args[0].length; i++) {
                        for (int j = i + 1; j < args[0].length; j++) {
                            JSONObject js = JSONUtil.parseObj(args[0][i]);
                            JSONObject js1 = JSONUtil.parseObj(args[0][j]);
                            String a = js.get("num").toString();
                            String b = js1.get("num").toString();
                            contentList.add(a + "," + a + "," + b);
                            contentList.add(a + "," + b + "," + a);
                            contentList.add(a + "," + b + "," + b);
                            contentList.add(b + "," + a + "," + b);
                            contentList.add(b + "," + b + "," + a);
                            contentList.add(b + "," + a + "," + a);
                        }
                    }
                }
                for (String str : contentList) {
                    PermutationVO permutation = new PermutationVO();
                    permutation.setMode(mode);
                    permutation.setContent(str);
                    permutation.setStageNumber(stageNumber);
                    permutation.setForecastBonus(new BigDecimal(price2 * times).setScale(2, RoundingMode.HALF_UP));
                    list.add(permutation);
                }
            } else if (mode.equals("2")) {
                //是否选了胆
                Boolean flag = false;
                //选中的胆的数字
                List<String> dan = new ArrayList<>();
                List<Object> objectList = Arrays.asList(args[0]);
                for (Object obj : objectList) {
                    JSONObject js = JSONUtil.parseObj(obj);
                    //判断是否选中了胆
                    if ((Boolean) js.get("isGallbladder")) {
                        flag = true;
                        dan.add(String.valueOf(js.get("num")));
                    }
                }
                if (flag) {
                    List<String> resList = new ArrayList<>();
                    for (int i = 0; i < args[0].length; i++) {
                        //通过第二层循环控制十位的数字    array[j]表示十位的值
                        for (int j = 0; j < args[0].length; j++) {
                            //通过第三层循环控制个位的数字   array[k]表示个位的值
                            for (int k = 0; k < args[0].length; k++) {
                                if (!JSONUtil.parseObj(args[0][i]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][j]).getStr("num"))
                                        && !JSONUtil.parseObj(args[0][j]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][k]).getStr("num")) && !JSONUtil.parseObj(args[0][i]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][k]).getStr("num"))) {
                                    resList.add(JSONUtil.parseObj(args[0][i]).getStr("num") + "," + JSONUtil.parseObj(args[0][j]).getStr("num") + "," + JSONUtil.parseObj(args[0][k]).getStr("num"));
                                }
                            }
                        }
                    }
                    //过滤胆
                    if (dan.size() == 1) {
                        resList = resList.stream().filter(res -> res.contains(dan.get(0))).collect(Collectors.toList());
                    } else {
                        resList = resList.stream().filter(res -> res.contains(dan.get(0)) && res.contains(dan.get(1))).collect(Collectors.toList());
                    }
                    for (String str : resList) {
                        PermutationVO permutation = new PermutationVO();
                        permutation.setMode(mode);
                        permutation.setContent(str);
                        permutation.setStageNumber(stageNumber);
                        permutation.setForecastBonus(new BigDecimal(price3 * times).setScale(2, RoundingMode.HALF_UP));
                        list.add(permutation);
                    }
                } else {
                    for (int i = 0; i < args[0].length; i++) {
                        //通过第二层循环控制十位的数字    array[j]表示十位的值
                        for (int j = 0; j < args[0].length; j++) {
                            //通过第三层循环控制个位的数字   array[k]表示个位的值
                            for (int k = 0; k < args[0].length; k++) {
                                if (!JSONUtil.parseObj(args[0][i]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][j]).getStr("num"))
                                        && !JSONUtil.parseObj(args[0][j]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][k]).getStr("num")) && !JSONUtil.parseObj(args[0][i]).getStr("num")
                                        .equals(JSONUtil.parseObj(args[0][k]).getStr("num"))) {
                                    PermutationVO permutation = new PermutationVO();
                                    permutation.setMode(mode);
                                    permutation.setContent(JSONUtil.parseObj(args[0][i]).getStr("num") + "," + JSONUtil.parseObj(args[0][j]).getStr("num") + "," + JSONUtil.parseObj(args[0][k]).getStr("num"));
                                    permutation.setStageNumber(stageNumber);
                                    permutation.setForecastBonus(new BigDecimal(price3 * times).setScale(2, RoundingMode.HALF_UP));
                                    list.add(permutation);
                                }
                            }
                        }
                    }
                }
            } else if (mode.equals("3")) {
                for (int i = 0; i < args[0].length; i++) {
                    PermutationVO permutation = new PermutationVO();
                    permutation.setMode(mode);
                    permutation.setContent(args[0][i] + "");
                    permutation.setStageNumber(stageNumber);
                    permutation.setForecastBonus(new BigDecimal(price1 * times).setScale(2, RoundingMode.HALF_UP));
                    list.add(permutation);
                }
            } else if (mode.equals("4")) {
                for (int i = 0; i < args[0].length; i++) {
                    PermutationVO permutation = new PermutationVO();
                    permutation.setMode(mode);
                    permutation.setContent(args[0][i] + "");
                    permutation.setStageNumber(stageNumber);
                    permutation.setForecastBonus(new BigDecimal(price2 * times).setScale(2, RoundingMode.HALF_UP));
                    list.add(permutation);
                }
            } else if (mode.equals("5")) {
                for (int i = 0; i < args[0].length; i++) {
                    for (int j = 0; j < args[1].length; j++) {
                        if (!args[0][i].equals(args[1][j])) {
                            PermutationVO permutation = new PermutationVO();
                            permutation.setMode(mode);
                            permutation.setContent(args[0][i] + "," + args[0][i] + "," + args[1][j]);
                            permutation.setStageNumber(stageNumber);
                            permutation.setForecastBonus(new BigDecimal(price2 * times).setScale(2, RoundingMode.HALF_UP));
                            list.add(permutation);
                        }
                    }
                }
            }
            //排列5
        } else if (type.equals(LotteryOrderTypeEnum.ARRANGE.getKey())) {
            //通过第一层循环控制百位的数字    array[i]表示百位的值
            for (int g = 0; g < args[0].length; g++) {
                for (int h = 0; h < args[1].length; h++) {
                    for (int i = 0; i < args[2].length; i++) {
                        //通过第二层循环控制十位的数字    array[j]表示十位的值
                        for (int j = 0; j < args[3].length; j++) {
                            //通过第三层循环控制个位的数字   array[k]表示个位的值
                            for (int k = 0; k < args[4].length; k++) {
                                PermutationVO permutation = new PermutationVO();
                                permutation.setMode(mode);
                                permutation.setContent(args[0][g] + "," + args[1][h] + "," + args[2][i] + "," + args[3][j] + "," + args[4][k]);
                                permutation.setStageNumber(stageNumber);
                                permutation.setForecastBonus(new BigDecimal(price4 * times).setScale(2, RoundingMode.HALF_UP));
                                list.add(permutation);
                            }
                        }
                    }
                }
            }
        } else if (type.equals(LotteryOrderTypeEnum.SEVEN_STAR.getKey())) {
            for (int e = 0; e < args[0].length; e++) {
                for (int f = 0; f < args[1].length; f++) {
                    for (int g = 0; g < args[2].length; g++) {
                        for (int h = 0; h < args[3].length; h++) {
                            for (int i = 0; i < args[4].length; i++) {
                                //通过第二层循环控制十位的数字    array[j]表示十位的值
                                for (int j = 0; j < args[5].length; j++) {
                                    //通过第三层循环控制个位的数字   array[k]表示个位的值
                                    for (int k = 0; k < args[6].length; k++) {
                                        PermutationVO permutation = new PermutationVO();
                                        permutation.setMode("七星彩");
                                        permutation.setContent(args[0][e] + "," + args[1][f] + "," + args[2][g] + "," + args[3][h] + "," + args[4][i] + "," + args[5][j] + "," + args[6][k]);
                                        permutation.setStageNumber(stageNumber);
                                        list.add(permutation);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * 排列三 组六开奖算法
     *
     * @param data   用户下注的数字列表
     * @param result 开奖结果
     * @return
     */
    public static Long groupSix(String data, String result) {
        Long price = 0L;
        JSONArray array = JSONUtil.parseArray(data);
        //是否选了胆
        Boolean flag = false;
        //选中的胆的数字
        List<String> dan = new ArrayList<>();
        for (Object obj : array) {
            JSONObject js = JSONUtil.parseObj(obj);
            //判断是否选中了胆
            if ((Boolean) js.get("isGallbladder")) {
                flag = true;
                dan.add(String.valueOf(js.get("num")));
            }
        }
        if (flag) {
            List<String> resList = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                //通过第二层循环控制十位的数字    array[j]表示十位的值
                for (int j = 0; j < array.size(); j++) {
                    //通过第三层循环控制个位的数字   array[k]表示个位的值
                    for (int k = 0; k < array.size(); k++) {
                        if (!JSONUtil.parseObj(array.get(i)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(j)).getStr("num"))
                                && !JSONUtil.parseObj(array.get(j)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(k)).getStr("num")) && !JSONUtil.parseObj(array.get(i)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(k)).getStr("num"))) {
                            resList.add(JSONUtil.parseObj(array.get(i)).getStr("num") + "," + JSONUtil.parseObj(array.get(j)).getStr("num") + "," + JSONUtil.parseObj(array.get(k)).getStr("num"));
                        }
                    }
                }
            }
            //过滤胆
            if (dan.size() == 1) {
                resList = resList.stream().filter(res -> res.contains(dan.get(0))).collect(Collectors.toList());
            } else {
                resList = resList.stream().filter(res -> res.contains(dan.get(0)) && res.contains(dan.get(1))).collect(Collectors.toList());
            }
            if (resList.contains(result)) {
                price = price3;
            }
        } else {
            //通过第一层循环控制百位的数字    array[i]表示百位的值
            for (int i = 0; i < array.size(); i++) {
                //通过第二层循环控制十位的数字    array[j]表示十位的值
                for (int j = 0; j < array.size(); j++) {
                    //通过第三层循环控制个位的数字   array[k]表示个位的值
                    for (int k = 0; k < array.size(); k++) {
                        if (!JSONUtil.parseObj(array.get(i)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(j)).getStr("num"))
                                && !JSONUtil.parseObj(array.get(j)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(k)).getStr("num")) && !JSONUtil.parseObj(array.get(i)).getStr("num")
                                .equals(JSONUtil.parseObj(array.get(k)).getStr("num"))) {
                            if (ObjectUtil.equal(result, JSONUtil.parseObj(array.get(i)).getStr("num") + "," + JSONUtil.parseObj(array.get(j)).getStr("num") + "," + JSONUtil.parseObj(array.get(k)).getStr("num"))) {
                                price = price3;
                            }
                        }
                    }
                }
            }
        }
        return price;
    }

    /**
     * 直选和值
     *
     * @param individualArr
     * @param result
     * @return
     */
    public static Long directlyElectedGentle(String[] individualArr, String result) {
        Long price = 0L;
        List<Integer> list = Convert.toList(Integer.class, result);
        Set<Integer> set = new HashSet<>(list);
        if (list.size() == set.size()) {
            for (String str : individualArr) {
                Integer sum = list.stream().mapToInt(Integer::intValue).sum();
                if (sum.equals(Integer.valueOf(str))) {
                    price = price1;
                }
            }
        }
        return price;
    }

    /**
     * 组选和值
     *
     * @param individualArr
     * @param result
     * @return
     */
    public static Long groupGentle(String[] individualArr, String result) {
        Long price = 0L;
        List<Integer> list = Convert.toList(Integer.class, result);
        Set<Integer> set = new HashSet<>(list);
        if (list.size() != set.size()) {
            for (String str : individualArr) {
                Integer sum = list.stream().mapToInt(Integer::intValue).sum();
                if (sum.equals(Integer.valueOf(str))) {
                    price = price2;
                }
            }
        }
        return price;
    }

    public static Long compound(String[] tenArr, String[] individualArr, String result) {
        Long price = 0L;
        for (int i = 0; i < tenArr.length; i++) {
            for (int j = 0; j < individualArr.length; j++) {
                if (tenArr[i] != individualArr[j]) {
                    String str = tenArr[i] + "," + tenArr[i] + "," + individualArr[j];
                    if (result.equals(str)) {
                        price = price2;
                    }
                }
            }
        }
        return price;
    }

    /**
     * 排列3 直选开奖算法
     *
     * @param hundredArr
     * @param tenArr
     * @param individualArr
     * @param result
     * @return
     */
    public static Long directlyElected(String[] hundredArr, String[] tenArr, String[] individualArr, String result) {
        Long price = 0L;
        //通过第一层循环控制百位的数字    array[i]表示百位的值
        for (int i = 0; i < hundredArr.length; i++) {
            //通过第二层循环控制十位的数字    array[j]表示十位的值
            for (int j = 0; j < tenArr.length; j++) {
                //通过第三层循环控制个位的数字   array[k]表示个位的值
                for (int k = 0; k < individualArr.length; k++) {
                    if (ObjectUtil.equal(result, hundredArr[i] + "," + tenArr[j] + "," + individualArr[k])) {
                        price = price1;
                    }
                }
            }
        }
        return price;
    }

    /**
     * 组三开奖算法
     *
     * @param data
     * @param result
     * @return
     */
    public static Long GroupThree(String data, String result) {
        Long price = 0L;
        List<String> list = new ArrayList<>();
        JSONArray str = JSONUtil.parseArray(data);
        //是否选了胆
        Boolean flag = false;
        //选中的胆的数字
        String dan = "";
        for (Object obj : str) {
            JSONObject js = JSONUtil.parseObj(obj);
            //判断是否选中了胆
            if ((Boolean) js.get("isGallbladder")) {
                flag = true;
                dan = String.valueOf(js.get("num"));
            }
        }
        if (flag) {
            //胆处理
            for (int i = 0; i < str.size(); i++) {
                for (int j = i + 1; j < str.size(); j++) {
                    JSONObject js = JSONUtil.parseObj(str.get(i));
                    JSONObject js1 = JSONUtil.parseObj(str.get(j));
                    String a = js.get("num").toString();
                    String b = js1.get("num").toString();
                    //过滤掉不是胆的组合
                    if (!a.equals(dan) && !b.equals(dan)) {
                        continue;
                    }
                    list.add(a + "," + a + "," + b);
                    list.add(a + "," + b + "," + a);
                    list.add(a + "," + b + "," + b);
                    list.add(b + "," + a + "," + b);
                    list.add(b + "," + b + "," + a);
                    list.add(b + "," + a + "," + a);
                }
            }
        } else {
            for (int i = 0; i < str.size(); i++) {
                for (int j = i + 1; j < str.size(); j++) {
                    JSONObject js = JSONUtil.parseObj(str.get(i));
                    JSONObject js1 = JSONUtil.parseObj(str.get(j));
                    String a = js.get("num").toString();
                    String b = js1.get("num").toString();
                    list.add(a + "," + a + "," + b);
                    list.add(a + "," + b + "," + a);
                    list.add(a + "," + b + "," + b);
                    list.add(b + "," + a + "," + b);
                    list.add(b + "," + b + "," + a);
                    list.add(b + "," + a + "," + a);
                }
            }
        }
        if (list.contains(result)) {
            price = price2;
        }
        return price;
    }

    /**
     * 排列5 开奖算法
     *
     * @param myriadArr
     * @param kiloArr
     * @param hundredArr
     * @param tenArr
     * @param individualArr
     * @param result
     * @return
     */
    public static Long arrangeElected(String[] myriadArr, String[] kiloArr, String[] hundredArr, String[]
            tenArr, String[] individualArr, String result) {
        Long price = 0L;
        //通过第一层循环控制百位的数字    array[i]表示百位的值
        for (int g = 0; g < myriadArr.length; g++) {
            for (int h = 0; h < kiloArr.length; h++) {
                for (int i = 0; i < hundredArr.length; i++) {
                    //通过第二层循环控制十位的数字    array[j]表示十位的值
                    for (int j = 0; j < tenArr.length; j++) {
                        //通过第三层循环控制个位的数字   array[k]表示个位的值
                        for (int k = 0; k < individualArr.length; k++) {
                            if (ObjectUtil.equal(result, myriadArr[g] + "," + kiloArr[h] + "," + hundredArr[i] + "," + tenArr[j] + "," + individualArr[k])) {
                                price = price4;
                            }
                        }
                    }
                }
            }
        }
        return price;
    }

    /**
     * 七星彩出奖计算
     *
     * @param hundredMyriadArr
     * @param tenMyriadArr
     * @param myriadArr
     * @param kiloArr
     * @param hundredArr
     * @param tenArr
     * @param individualArr
     * @param result
     * @param moneyAward       每注中奖金额，由官网出奖
     * @return
     */
    public static Long sevenStarLottery(String[] hundredMyriadArr, String[] tenMyriadArr, String[] myriadArr, String[]
            kiloArr, String[] hundredArr, String[] tenArr, String[] individualArr, String result, String moneyAward) {
        List<String> codeList = new ArrayList<>();
        for (String hundredMyriad : hundredMyriadArr) {
            for (String tenMyriad : tenMyriadArr) {
                for (String myriad : myriadArr) {
                    for (String kilo : kiloArr) {
                        for (String hundred : hundredArr) {
                            for (String ten : tenArr) {
                                for (String individual : individualArr) {
                                    String code = hundredMyriad + "," + tenMyriad + "," + myriad + "," + kilo + "," + hundred + "," + ten + "," + individual;
                                    codeList.add(code);
                                }
                            }
                        }
                    }
                }
            }
        }
        Long totalPrice = sevenStarComputing(codeList, result, moneyAward);
        return totalPrice;
    }

    /**
     * 七星彩计算奖金
     *
     * @param codeList
     * @param target
     * @param moneyAward 每注中奖金额，由官网出奖
     * @return
     */
    private static Long sevenStarComputing(List<String> codeList, String target, String moneyAward) {
        Long price = 0L;
        for (String str : codeList) {
            //一等奖6+1
            if (str.equals(target)) {
                price += 5000000;
                continue;
            }
            //二等奖6+0
            List<String> targetList = Arrays.asList(target.split(","));
            String regexTwo = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String res = str.substring(0, 11);
            if (res.matches(regexTwo)) {
                price += Long.valueOf(moneyAward);
                continue;
            }
            //三等奖5+1
            String rageThree1 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageThree2 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";

            if (res.matches(rageThree1) || res.matches(rageThree2)) {
                if (targetList.get(6).equals(str.substring(12))) {
                    price += 3000;
                    continue;
                }
            }

            //四等奖5+0
            String rageFour1 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageFour2 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";

            if (res.matches(rageFour1) || res.matches(rageFour2)) {
                price += 500;
                continue;
            }
            //四等奖4+1
            String rageFour3 = "[0-9],[0-9],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageFour4 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";
            String rageFour5 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[0-9],[0-9]";
            if (res.matches(rageFour3) || res.matches(rageFour4) || res.matches(rageFour5)) {
                if (targetList.get(6).equals(str.substring(12))) {
                    price += 500;
                    continue;
                }
            }

            //五等奖4+0
            String rageFive1 = "[0-9],[0-9],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageFive2 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";
            String rageFive3 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[0-9],[0-9]";

            if (res.matches(rageFive1) || res.matches(rageFive2) || res.matches(rageFive3)) {
                price += 30;
                continue;
            }

            //五等奖3+1
            String rageFive4 = "[0-9],[0-9],[0-9],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageFive5 = "[0-9],[0-9],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";
            String rageFive6 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[0-9],[0-9]";
            String rageFive7 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[0-9],[0-9],[0-9]";

            if (res.matches(rageFive4) || res.matches(rageFive5) || res.matches(rageFive6) || res.matches(rageFive7)) {
                if (targetList.get(6).equals(str.substring(12))) {
                    price += 30;
                    continue;
                }
            }

            //六等奖3+0
            String rageSix1 = "[0-9],[0-9],[0-9],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            String rageSix2 = "[0-9],[0-9],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";
            String rageSix3 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[0-9],[0-9]";
            String rageSix4 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[0-9],[0-9],[0-9]";
            if (res.matches(rageSix1) || res.matches(rageSix2) || res.matches(rageSix3) || res.matches(rageSix4)) {
                price += 5;
                continue;
            }

            //六等奖2+1
            String rageSix5 = "[" + targetList.get(0) + "],[" + targetList.get(1) + "],[0-9],[0-9],[0-9],[0-9]";
            String rageSix6 = "[0-9],[" + targetList.get(1) + "],[" + targetList.get(2) + "],[0-9],[0-9],[0-9]";
            String rageSix7 = "[0-9],[0-9],[" + targetList.get(2) + "],[" + targetList.get(3) + "],[0-9],[0-9]";
            String rageSix8 = "[0-9],[0-9],[0-9],[" + targetList.get(3) + "],[" + targetList.get(4) + "],[0-9]";
            String rageSix9 = "[0-9],[0-9],[0-9],[0-9],[" + targetList.get(4) + "],[" + targetList.get(5) + "]";
            if (res.matches(rageSix5) || res.matches(rageSix6) || res.matches(rageSix7) || res.matches(rageSix8) || res.matches(rageSix9)) {
                if (targetList.get(6).equals(str.substring(12))) {
                    price += 5;
                    continue;
                }
            }
            //六等奖1+1
            String rageSix10 = "[0-9],[0-9],[0-9],[0-9],[0-9],[" + targetList.get(5) + "]";
            String rageSix11 = "[" + targetList.get(0) + "],[0-9],[0-9],[0-9],[0-9],[0-9]";
            String rageSix12 = "[0-9],[" + targetList.get(1) + "],[0-9],[0-9],[0-9],[0-9]";
            String rageSix13 = "[0-9],[0-9],[" + targetList.get(2) + "],[0-9],[0-9],[0-9]";
            String rageSix14 = "[0-9],[0-9],[0-9],[" + targetList.get(3) + "],[0-9],[0-9]";
            String rageSix15 = "[0-9],[0-9],[0-9],[0-9],[" + targetList.get(4) + "],[0-9]";
            if (res.matches(rageSix10) || res.matches(rageSix11) || res.matches(rageSix12) || res.matches(rageSix13) || res.matches(rageSix14) || res.matches(rageSix15)) {
                if (targetList.get(6).equals(str.substring(12))) {
                    price += 5;
                    continue;
                }

            }
            //六等奖0+1
            if (targetList.get(6).equals(str.substring(12))) {
                price += 5;
                continue;
            }
        }
        return price;
    }
}
