package com.qihang.controller.notice.app;


import cn.hutool.core.bean.BeanUtil;
import com.qihang.common.vo.CommonListVO;
import com.qihang.controller.notice.app.vo.NoticeVO;
import com.qihang.service.notice.INoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author qihang
 * @since 2022-10-08
 */
@RestController
@RequestMapping("/app/notice")
public class AppNoticeController {
    @Resource
    private INoticeService noticeService;

    @GetMapping("/list")
    @ApiOperation("公告列表")
    public CommonListVO<NoticeVO> list() {
        CommonListVO<NoticeVO> commonList = new CommonListVO();
        List<NoticeVO> list = BeanUtil.copyToList(noticeService.list(), NoticeVO.class);
        commonList.setVoList(list);
        return commonList;
    }
}
