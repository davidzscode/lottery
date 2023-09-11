package com.qihang.common.util.order;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;

import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022/10/10 15:26
 */
public class OrderNumberGenerationUtil {

    public static String getOrderId() {
        return DateUtil.format(new Date(), "yyyyMMdd") + IdUtil.getSnowflake((long) (0 + Math.random() * (30 - 0 + 1)), (long) (0 + Math.random() * (30 - 0 + 1))).nextId();
    }
}
