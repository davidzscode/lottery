package com.qihang.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.dict.dto.DeleteAliPayDictDTO;
import com.qihang.controller.dict.dto.EditAliPayDictDTO;
import com.qihang.controller.dict.dto.SysDictDTO;
import com.qihang.controller.dict.vo.SysDictQueryVO;
import com.qihang.domain.dict.SysDictDO;

/**
 * @author qihang
 * @since 2022-11-14
 */
public interface ISysDictService extends IService<SysDictDO> {
    /**
     * 添加
     * @param sysDict
     */
    BaseVO insert(SysDictDTO sysDict);
    
    /**
     * 根据租户id查询数据字典
     * @param tenantId
     */
    CommonListVO<SysDictQueryVO> queryDictByTenantId(Integer tenantId);
    
    
    CommonListVO<Integer> queryChannelIdsByTenantId(Integer tenantId);
    
    BaseVO addAliPayKey(SysDictDTO sysDict);
    
    BaseVO deleteAliPayKey(DeleteAliPayDictDTO dto);
    
    BaseVO editAliPayKey(EditAliPayDictDTO dto);
    
}
