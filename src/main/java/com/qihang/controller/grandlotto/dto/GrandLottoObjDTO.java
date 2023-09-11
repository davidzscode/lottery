package com.qihang.controller.grandlotto.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2023-04-05 11:40
 */
@Data
public class GrandLottoObjDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean active;

    private Boolean isGallbladder;

    private String num;
}
