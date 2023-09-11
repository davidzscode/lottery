package com.qihang.service.grandlotto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qihang.common.util.reward.GrandLottoUtil;
import com.qihang.controller.grandlotto.dto.GrandLottoDTO;
import com.qihang.controller.grandlotto.dto.GrandLottoObjDTO;
import com.qihang.controller.grandlotto.vo.GrandLottoVO;
import com.qihang.controller.permutation.app.vo.PermutationVO;
import com.qihang.domain.permutation.PermutationAwardDO;
import com.qihang.enumeration.order.lottery.LotteryOrderTypeEnum;
import com.qihang.mapper.permutation.PermutationAwardMapper;
import com.qihang.mapper.permutation.PermutationMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: qihang
 * @description:
 * @time: 2023-04-05 11:35
 */
@Service
public class GrandLottoServiceImpl implements IGrandLottoService {

    @Resource
    private PermutationAwardMapper permutationAwardMapper;
    @Override
    public GrandLottoVO calculation(GrandLottoDTO grandLotto) {
        PermutationAwardDO permutationAward = permutationAwardMapper.selectOne(new QueryWrapper<PermutationAwardDO>().lambda().eq(PermutationAwardDO::getType, LotteryOrderTypeEnum.GRAND_LOTTO.getKey()).orderByDesc(PermutationAwardDO::getCreateTime).last("limit 1"));

        GrandLottoVO grandLottoVO = new GrandLottoVO();

        //获取对应的下单号码
        List<String> list = GrandLottoUtil.calculation(grandLotto.getRedList(),grandLotto.getBlueList());

        grandLottoVO.setNotes(list.size());
        List<PermutationVO> permutationList = new ArrayList<>();
        for (String s : list){
            PermutationVO permutationVO = new PermutationVO();
            permutationVO.setContent(s);
            permutationVO.setMode("大乐透");
            permutationVO.setStageNumber(permutationAward.getStageNumber()+1);
            permutationList.add(permutationVO);
        }
        grandLottoVO.setPermutationList(permutationList);
        return grandLottoVO;
    }
}
