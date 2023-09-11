package com.qihang.service.complaints;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.complaints.dto.ComplaintsAnswerDTO;
import com.qihang.controller.complaints.dto.ComplaintsCreateDTO;
import com.qihang.controller.complaints.dto.ComplaintsDetailDTO;
import com.qihang.controller.complaints.dto.ComplaintsListDTO;
import com.qihang.controller.complaints.vo.ComplaintsContentVO;
import com.qihang.controller.complaints.vo.ComplaintsListVO;
import com.qihang.domain.complaints.ComplaintsDO;

import javax.validation.Valid;

/**
 * @author lihv
 * @since 2023/7/27
 */
public interface IComplaintsService extends IService<ComplaintsDO> {
    CommonListVO<ComplaintsListVO> getPageList(@Valid ComplaintsListDTO complaintsDO, Integer userId);
    
    BaseVO create(@Valid ComplaintsCreateDTO complaintsDO, Integer userId);
    
    BaseVO answer(@Valid ComplaintsAnswerDTO complaintsDO, Integer userId);
    
    ComplaintsContentVO detail(@Valid ComplaintsDetailDTO complaintsCreateDTO, Integer valueOf);
}
