package com.qihang.service.omit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.controller.omit.app.vo.OmitVO;
import com.qihang.domain.omit.OmitDO;
import com.qihang.mapper.omit.OmitMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qihang
 * @since 2023-04-07
 */
@Service
public class OmitServiceImpl extends ServiceImpl<OmitMapper, OmitDO> implements IOmitService {

    @Resource
    private OmitMapper omitMapper;

    @Override
    public OmitVO getOmitByType(String type) {
        OmitVO omitVO = new OmitVO();
        OmitDO omitDO = omitMapper.selectOne(new QueryWrapper<OmitDO>().lambda().eq(OmitDO::getType, type));
        BeanUtils.copyProperties(omitDO, omitVO);
        return omitVO;
    }
}
