package com.qihang.service.realm;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.realm.admin.dto.SysDomainDTO;
import com.qihang.controller.realm.admin.vo.SysDomainVO;
import com.qihang.domain.realm.SysDomainDO;

/**
 * @author qihang
 * @since 2023-02-27
 */
public interface ISysDomainService extends IService<SysDomainDO> {

    /**
     * app列表
     *
     * @return
     */
    CommonListVO<SysDomainVO> queryDomain();


    /**
     * 修改
     *
     * @param sysDomain
     * @param id
     * @return
     */
    BaseVO update(SysDomainDTO sysDomain, Integer id);

}
