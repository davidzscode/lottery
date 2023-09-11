package com.qihang.service.notice;

import com.qihang.domain.notice.NoticeDO;
import com.qihang.mapper.notice.NoticeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author qihang
 * @since 2022-10-08
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeDO> implements INoticeService {

}
