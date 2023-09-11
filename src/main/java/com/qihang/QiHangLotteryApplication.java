package com.qihang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qihang
 * Email：evi2009kt@gmail.com
 * 飞机Telegram：qihang9981
 */
@SpringBootApplication
@MapperScan("com.qihang.mapper.**")
public class QiHangLotteryApplication {
    public static void main(String[] args) {
        SpringApplication.run(QiHangLotteryApplication.class, args);
    }
}



