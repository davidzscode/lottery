package com.qihang.controller.notice.admin;


import cn.hutool.core.bean.BeanUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.notice.admin.dto.NoticeUpdateDTO;
import com.qihang.controller.notice.admin.vo.NoticeQueryVO;
import com.qihang.domain.notice.NoticeDO;
import com.qihang.service.notice.INoticeService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author qihang
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    @Resource
    private INoticeService noticeService;

    @GetMapping("/list")
    @ApiOperation("公告列表")
    public CommonListVO<NoticeQueryVO> noticeList() {
        CommonListVO<NoticeQueryVO> commonList = new CommonListVO();
        List<NoticeQueryVO> list = BeanUtil.copyToList(noticeService.list(), NoticeQueryVO.class);
        commonList.setVoList(list);
        return commonList;
    }

    @PutMapping("/update/{id}")
    @ApiOperation("修改公告")
    public BaseVO noticeUpdate(@PathVariable("id") Integer id, @RequestBody @Valid NoticeUpdateDTO noticeUpdate) {
        NoticeDO notice = new NoticeDO();
        notice.setId(id);
        notice.setMsg(noticeUpdate.getMsg());
        noticeService.updateById(notice);
        return new BaseVO();
    }



    @PutMapping("/updateStatus/{id}")
    @ApiModelProperty("修改公告开关")
    public BaseVO setNoticeStatus(@PathVariable("id")Integer id,@RequestBody @Valid Integer status){
        NoticeDO notice = new NoticeDO();
        notice.setId(id);
        notice.setStatus(status);
        noticeService.updateById(notice);
        return new BaseVO();
    }
}
