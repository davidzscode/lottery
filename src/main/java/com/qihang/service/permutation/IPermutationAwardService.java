package com.qihang.service.permutation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.controller.permutation.app.vo.IssueNoVO;
import com.qihang.domain.permutation.PermutationAwardDO;

/**
 * @author qihang
 * @since 2022-10-10
 */
public interface IPermutationAwardService extends IService<PermutationAwardDO> {
    /**
     * 获取期号
     *
     * @param type
     * @return
     */
    IssueNoVO getIssueNo(String type);


}
