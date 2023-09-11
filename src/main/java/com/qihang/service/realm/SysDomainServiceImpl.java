package com.qihang.service.realm;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.annotation.TenantIgnore;
import com.qihang.common.util.code.QrCodeUtil;
import com.qihang.common.util.upload.S3Util;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.realm.admin.dto.SysDomainDTO;
import com.qihang.controller.realm.admin.vo.SysDomainVO;
import com.qihang.domain.realm.SysDomainDO;
import com.qihang.domain.user.UserDO;
import com.qihang.mapper.realm.SysDomainMapper;
import com.qihang.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author qihang
 * @since 2023-02-27
 */
@Service
public class SysDomainServiceImpl extends ServiceImpl<SysDomainMapper, SysDomainDO> implements ISysDomainService {

    @Resource
    private SysDomainMapper sysDomainMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private S3Util s3Util;

    @Override
    public CommonListVO<SysDomainVO> queryDomain() {
        CommonListVO<SysDomainVO> commonList = new CommonListVO<>();
        List<SysDomainDO> domainList = sysDomainMapper.selectList(null);
        List<SysDomainVO> list = BeanUtil.copyToList(domainList, SysDomainVO.class);
        commonList.setVoList(list);
        return commonList;
    }

    @Override
    @TenantIgnore
    public BaseVO update(SysDomainDTO sysDomain, Integer id) {
        SysDomainDO sysDomainDO = new SysDomainDO();
        BeanUtil.copyProperties(sysDomain, sysDomainDO);
        sysDomainDO.setId(id);
        sysDomainDO.setUpdateTime(new Date());
        sysDomainMapper.updateById(sysDomainDO);
        //修改二维码地址
        List<UserDO> userList = userMapper.selectList(null);
        for (UserDO userDO : userList) {
            InputStream is = QrCodeUtil.generate(sysDomain.getAppUrl() + "/#/pages/user/register?uid=" + userDO.getUid());
            String codeUrl = s3Util.upload(is);
            userDO.setQrCode(codeUrl);
            userMapper.updateById(userDO);
        }
        return new BaseVO();
    }
}
