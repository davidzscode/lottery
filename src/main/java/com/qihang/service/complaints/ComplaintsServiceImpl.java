package com.qihang.service.complaints;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.complaints.dto.ComplaintsAnswerDTO;
import com.qihang.controller.complaints.dto.ComplaintsCreateDTO;
import com.qihang.controller.complaints.dto.ComplaintsDetailDTO;
import com.qihang.controller.complaints.dto.ComplaintsListDTO;
import com.qihang.controller.complaints.vo.ComplaintsContentVO;
import com.qihang.controller.complaints.vo.ComplaintsListVO;
import com.qihang.domain.complaints.ComplaintsDO;
import com.qihang.domain.user.UserDO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import com.qihang.mapper.complaints.ComplaintsMapper;
import com.qihang.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 投诉表
 * @author lihv
 * @since 2023/7/27
 */
@Service
@Slf4j
public class ComplaintsServiceImpl extends ServiceImpl<ComplaintsMapper, ComplaintsDO> implements IComplaintsService {
    
    @Resource
    private ComplaintsMapper complaintsMapper;
    @Resource
    private UserMapper userMapper;
    
    
    @Override
    public CommonListVO<ComplaintsListVO> getPageList(@Valid ComplaintsListDTO complaintsDO, Integer userId) {
        CommonListVO<ComplaintsListVO> commonList = new CommonListVO<>();
        //分页
        Page<ComplaintsDO> page = new Page<>(complaintsDO.getPageNo(), complaintsDO.getPageSize());
        //动态拼接查询条件
        LambdaQueryWrapper<ComplaintsDO> qw = new QueryWrapper<ComplaintsDO>().lambda();
        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null) {
            log.error("userId不能为空");
            return commonList;
        }
        if (userDO.getIsAgent().equals("1")) {
            if (userDO.getTenantId() == null) {
                log.error("店主TenantId不能为空");
                return commonList;
            }
            qw.eq(ComplaintsDO::getTenantId, userDO.getTenantId());
        } else {
            qw.eq(ComplaintsDO::getUserId, userId);
        }
        if (complaintsDO.getStartTime() != null) {
            qw.ge(ComplaintsDO::getAddTime, complaintsDO.getStartTime());
        }
        if (complaintsDO.getEndTime() != null) {
            qw.le(ComplaintsDO::getAddTime, complaintsDO.getEndTime());
        }
        qw.orderBy(true, true, ComplaintsDO::getIfAnswer);
        qw.orderBy(true, false, ComplaintsDO::getId);
        Page<ComplaintsDO> payOrderPage = complaintsMapper.selectPage(page, qw);
        List<Integer> userIdList = payOrderPage.getRecords().stream().map(ComplaintsDO::getUserId).distinct().collect(Collectors.toList());
        Map<Integer, String> userMap = new HashMap<>();
        if (!userIdList.isEmpty()) {
            List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().in(UserDO::getId, userIdList));
            for (UserDO aDo : userDOList) {
                if (aDo.getId() != null) {
                    userMap.put(aDo.getId(), aDo.getName());
                }
            }
        }
        List<ComplaintsListVO> listVOS = BeanUtil.copyToList(payOrderPage.getRecords(), ComplaintsListVO.class);
        for (ComplaintsListVO vo : listVOS) {
            String name = userMap.get(vo.getUserId());
            if (StringUtils.hasLength(name)) {
                vo.setUserName(name);
            } else if (vo.getUserId() != null) {
                vo.setUserName(vo.getUserId().toString());
            }
        }
        commonList.setTotal(payOrderPage.getTotal());
        commonList.setVoList(listVOS);
        return commonList;
    }
    
    @Override
    public ComplaintsContentVO detail(@Valid ComplaintsDetailDTO complaintsCreateDTO, Integer userId) {
        ComplaintsDO complaintsDO = complaintsMapper.selectById(complaintsCreateDTO.getId());
        ComplaintsContentVO vo = new ComplaintsContentVO();
        BeanUtil.copyProperties(complaintsDO, vo);
        UserDO userDO = userMapper.selectById(vo.getUserId());
        if (userDO != null) {
            vo.setUserName(StringUtils.hasLength(userDO.getName()) ? userDO.getName() : userDO.getId().toString());
        }
        return vo;
    }
    
    @Override
    public BaseVO create(@Valid ComplaintsCreateDTO dto, Integer userId) {
        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null || userDO.getTenantId() == null) {
            log.info("用户未绑定商铺，无法评论");
            return new BaseVO(false, ErrorCodeEnum.E103.getKey(), ErrorCodeEnum.E103.getValue());
        }
        ComplaintsDO cd = new ComplaintsDO();
        cd.setTitle(dto.getTitle());
        cd.setContent(dto.getContent());
        cd.setUserId(userId);
        cd.setTenantId(userDO.getTenantId());
        cd.setAddTime(new Date());
        cd.setIfAnswer(0);
        complaintsMapper.insert(cd);
        return new BaseVO();
    }
    
    @Override
    public BaseVO answer(@Valid ComplaintsAnswerDTO complaintsDO, Integer userId) {
        UserDO userDO = userMapper.selectById(userId);
        if (userDO == null || userDO.getIsAgent().equals("0")) {
            log.info("用户不能回复");
            return new BaseVO(false, ErrorCodeEnum.E103.getKey(), ErrorCodeEnum.E103.getValue());
        }
        ComplaintsDO cd = new ComplaintsDO();
        cd.setId(complaintsDO.getId());
        cd.setAnswer(complaintsDO.getAnswer());
        cd.setAnsTime(new Date());
        cd.setIfAnswer(1);
        complaintsMapper.updateById(cd);
        return new BaseVO();
    }
    
    
}
