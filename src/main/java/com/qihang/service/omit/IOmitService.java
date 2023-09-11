package com.qihang.service.omit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.controller.omit.app.vo.OmitVO;
import com.qihang.domain.omit.OmitDO;

/**
 * @author qihang
 * @since 2023-04-07
 */
public interface IOmitService extends IService<OmitDO> {

    /**
     * 根据类型查询遗漏
     *
     * @param type
     * @return
     */
    OmitVO getOmitByType(String type);
}
