package com.qihang.common.util.reward;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author: qihang
 * @description: 彩票算法工具类
 * @time: 2022-10-15 15:18
 */
public class LotteryAlgorithmUtil {
    /**
     * 彩票截止时间算法
     *
     * @param date      开赛时间
     * @param startTime 属于哪一天的比赛
     * @return
     */
    public static Date calculationDeadline(String date, String startTime) {

        //属于哪一天的开赛时间
        String numDate = startTime.substring(0, startTime.indexOf(" "));
        //获取年份
        int year = DateUtil.thisYear();
        //开赛时间
        DateTime openTime = DateUtil.parse(year + "-" + date);

        DateTime endDate = null;
        //判断是否是周末
        if (DateUtil.parse(numDate).isWeekend()) {
            endDate = DateUtil.parse(numDate + " 22:55");
        } else {
            endDate = DateUtil.parse(numDate + " 21:55");
        }

        //Date1 时间在 Date2 之前
        if (openTime.compareTo(endDate) < 0) {
            //如果是在截止时间之前需要提前5分钟
            endDate = DateUtil.offsetMinute(openTime, -5);
        }
        return endDate;
    }
}
