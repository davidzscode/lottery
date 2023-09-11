package com.qihang.service.dict;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.constant.Constant;
import com.qihang.controller.dict.dto.DeleteAliPayDictDTO;
import com.qihang.controller.dict.dto.EditAliPayDictDTO;
import com.qihang.controller.dict.dto.SysDictDTO;
import com.qihang.controller.dict.vo.SysDictQueryVO;
import com.qihang.domain.dict.SysDictDO;
import com.qihang.mapper.dict.SysDictMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qihang
 * @since 2022-11-14
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictDO> implements ISysDictService {
    
    @Resource
    private SysDictMapper sysDictMapper;
    
    @Override
    @TenantIgnore
    @Transactional(rollbackFor = Exception.class)
    public BaseVO insert(SysDictDTO sysDict) {
        
        sysDictMapper.delete(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getTenantId, sysDict.getTenantId()));
        
        SysDictDO sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_APPID_KAY);
        sysDictDO.setValue(sysDict.getAliPayAppId());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝appid");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictMapper.insert(sysDictDO);
        
        sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_PUBLIC_KAY);
        sysDictDO.setValue(sysDict.getAlipayPublicKey());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝公钥");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictMapper.insert(sysDictDO);
        
        sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_PRIVATE_KAY);
        sysDictDO.setValue(sysDict.getAlipayPrivateKey());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝私钥");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictMapper.insert(sysDictDO);
        return new BaseVO();
    }
    
    /**
     * @param sysDict
     */
    @Override
    @Transactional
    public BaseVO addAliPayKey(SysDictDTO sysDict) {
        int channelId = 0;
        List<Object> objects = sysDictMapper.selectObjs(new QueryWrapper<SysDictDO>().select("max(channel_id)")
                .lambda().eq(SysDictDO::getTenantId, sysDict.getTenantId()));
        if (!objects.isEmpty() && objects.get(0) != null) {
            channelId = Integer.parseInt(objects.get(0).toString());
        }
        channelId++;
        
        SysDictDO sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_APPID_KAY);
        sysDictDO.setValue(sysDict.getAliPayAppId());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝appid");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictDO.setChannelId(channelId);
        sysDictMapper.insert(sysDictDO);
        
        sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_PUBLIC_KAY);
        sysDictDO.setValue(sysDict.getAlipayPublicKey());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝公钥");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictDO.setChannelId(channelId);
        sysDictMapper.insert(sysDictDO);
        
        sysDictDO = new SysDictDO();
        sysDictDO.setCode(Constant.ALIPAY_PRIVATE_KAY);
        sysDictDO.setValue(sysDict.getAlipayPrivateKey());
        sysDictDO.setTenantId(sysDict.getTenantId());
        sysDictDO.setDictDesc("支付宝私钥");
        sysDictDO.setCreateTime(new Date());
        sysDictDO.setUpdateTime(new Date());
        sysDictDO.setChannelId(channelId);
        int insert = sysDictMapper.insert(sysDictDO);
        return new BaseVO(insert > 0, null, null);
    }
    
    /**
     * @param dto
     */
    @Override
    public BaseVO editAliPayKey(EditAliPayDictDTO dto) {
        
        SysDictDO sysDictDO = new SysDictDO();
        LambdaUpdateWrapper<SysDictDO> updateWrapper = new UpdateWrapper<SysDictDO>().lambda()
                .eq(SysDictDO::getTenantId, dto.getTenantId()).eq(SysDictDO::getChannelId, dto.getChannelId());
        updateWrapper.eq(SysDictDO::getCode, Constant.ALIPAY_APPID_KAY);
        sysDictDO.setValue(dto.getAliPayAppId());
        sysDictDO.setUpdateTime(new Date());
        sysDictMapper.update(sysDictDO, updateWrapper);
        
        sysDictDO = new SysDictDO();
        updateWrapper = new UpdateWrapper<SysDictDO>().lambda()
                .eq(SysDictDO::getTenantId, dto.getTenantId()).eq(SysDictDO::getChannelId, dto.getChannelId());
        updateWrapper.eq(SysDictDO::getCode, Constant.ALIPAY_PUBLIC_KAY);
        sysDictDO.setValue(dto.getAlipayPublicKey());
        sysDictDO.setUpdateTime(new Date());
        sysDictMapper.update(sysDictDO, updateWrapper);
        
        sysDictDO = new SysDictDO();
        updateWrapper = new UpdateWrapper<SysDictDO>().lambda()
                .eq(SysDictDO::getTenantId, dto.getTenantId()).eq(SysDictDO::getChannelId, dto.getChannelId());
        updateWrapper.eq(SysDictDO::getCode, Constant.ALIPAY_PRIVATE_KAY);
        sysDictDO.setValue(dto.getAlipayPrivateKey());
        sysDictDO.setUpdateTime(new Date());
        int insert = sysDictMapper.update(sysDictDO, updateWrapper);
        return new BaseVO(insert > 0, null, null);
    }
    
    /**
     * @param dto
     */
    @Override
    public BaseVO deleteAliPayKey(DeleteAliPayDictDTO dto) {
        int delete = sysDictMapper.delete(new QueryWrapper<SysDictDO>().lambda()
                .eq(SysDictDO::getTenantId, dto.getTenantId()).eq(SysDictDO::getChannelId, dto.getChannelId()));
        return new BaseVO(delete > 0, null, null);
    }
    
    @Override
    @TenantIgnore
    public CommonListVO<SysDictQueryVO> queryDictByTenantId(Integer tenantId) {
        CommonListVO<SysDictQueryVO> commonList = new CommonListVO<>();
        List<SysDictDO> dictList = sysDictMapper.selectList(new QueryWrapper<SysDictDO>().lambda().eq(SysDictDO::getTenantId, tenantId));
        List<SysDictQueryVO> list = BeanUtil.copyToList(dictList, SysDictQueryVO.class);
        commonList.setVoList(list);
        return commonList;
    }
    
    /**
     * @param tenantId
     */
    @Override
    public CommonListVO<Integer> queryChannelIdsByTenantId(Integer tenantId) {
        CommonListVO<Integer> commonList = new CommonListVO<>();
        List<SysDictDO> sysDictDOS = sysDictMapper.selectList(new QueryWrapper<SysDictDO>().lambda()
                .eq(SysDictDO::getTenantId, tenantId).groupBy(SysDictDO::getChannelId));
        List<Integer> channelIds = sysDictDOS.stream().map(SysDictDO::getChannelId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        commonList.setVoList(channelIds);
        return commonList;
    }
    
    
}
