package com.qihang.service.grandlotto;

import com.qihang.controller.grandlotto.dto.GrandLottoDTO;
import com.qihang.controller.grandlotto.vo.GrandLottoVO;

/**
 * @author: qihang
 * @description:
 * @time: 2023-04-05 11:35
 */
public interface IGrandLottoService {


    /**
     * 计算 组 注 预测金额
     *
     * @param grandLotto
     * @return
     */
    GrandLottoVO calculation(GrandLottoDTO grandLotto);
}
