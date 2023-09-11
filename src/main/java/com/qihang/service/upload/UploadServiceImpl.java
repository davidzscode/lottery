package com.qihang.service.upload;

import com.qihang.common.util.upload.S3Util;
import com.qihang.common.vo.BaseVO;
import com.qihang.controller.upload.vo.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-17 18:30
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Resource
    private S3Util s3Util;

    @Override
    public BaseVO upload(MultipartFile file) {
        FileVO fileVO = new FileVO();
        String url = s3Util.upload(file);
        fileVO.setUrl(url);
        return fileVO;
    }
}
