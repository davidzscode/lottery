package com.qihang.service.permutation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.controller.permutation.app.vo.IssueNoVO;
import com.qihang.domain.permutation.PermutationAwardDO;
import com.qihang.mapper.permutation.PermutationAwardMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2022-10-10
 */
@Service
public class PermutationAwardServiceImpl extends ServiceImpl<PermutationAwardMapper, PermutationAwardDO> implements IPermutationAwardService {

    @Resource
    private PermutationAwardMapper permutationAwardMapper;

    @Override
    public IssueNoVO getIssueNo(String type) {
        IssueNoVO issueNo = new IssueNoVO();
        PermutationAwardDO permutationAward = permutationAwardMapper.selectOne(new QueryWrapper<PermutationAwardDO>().lambda().eq(PermutationAwardDO::getType, type).orderByDesc(PermutationAwardDO::getCreateTime).last("limit 1"));
        permutationAward.setStageNumber(permutationAward.getStageNumber() + 1);
        BeanUtils.copyProperties(permutationAward, issueNo);
        return issueNo;
    }
}
